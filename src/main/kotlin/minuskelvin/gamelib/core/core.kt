package minuskelvin.gamelib.core

import minuskelvin.gamelib.input.InputController
import minuskelvin.gamelib.input.PlaybackInputController
import minuskelvin.gamelib.input.RecordingInputController
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import java.io.*
import java.util.*

class Game<Context> internal constructor() {
    var state: Updateable<Context>? = null
    val rng = Random()
}

fun<Context> launch(args: Array<String>, initialWindowSettings: WindowSettings, ups: Double, initializer: (Game<Context>, InputController) -> Context) {
    var path: File? = null
    var recording = false
    var speed = 1.0

    val iter = args.iterator()
    while (iter.hasNext()) {
        when (iter.next()) {
            "record" -> {
                if (path != null)
                    usererror("Multiple record/play parameters")
                if (!iter.hasNext())
                    usererror("record parameter requires file path next")
                path = File(iter.next())
                recording = true
            }
            "play" -> {
                if (path != null)
                    usererror("Multiple record/play parameters")
                if (!iter.hasNext())
                    usererror("record parameter requires file path next")
                path = File(iter.next())
                recording = false
            }
            "speed" -> {
                if (!iter.hasNext())
                    usererror("speed parameter requires a speed next")
                speed = iter.next().toDoubleOrNull() ?: usererror("Not a number")
                if (speed <= 0.0)
                    usererror("Speed cannot be less than or equal to zero")
            }
        }
    }
    
    val errorCB = GLFWErrorCallback.createThrow()!!
    glfwSetErrorCallback(errorCB)

    glfwInit()

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)

    val window = when (initialWindowSettings) {
        is WindowedFullscreen -> {
            val monitor = glfwGetPrimaryMonitor()
            val mode = glfwGetVideoMode(monitor)!!
            glfwWindowHint(GLFW_RED_BITS, mode.redBits())
            glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits())
            glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits())
            glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate())
            glfwCreateWindow(mode.width(), mode.height(), initialWindowSettings.title, monitor, 0)
        }
        is Maximised -> {
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE)
            glfwWindowHint(GLFW_DECORATED, if (initialWindowSettings.undecorated) GLFW_FALSE else GLFW_TRUE)
            glfwCreateWindow(1280, 720, initialWindowSettings.title, 0, 0)
        }
        is Windowed -> {
            glfwWindowHint(GLFW_DECORATED, if (initialWindowSettings.undecorated) GLFW_FALSE else GLFW_TRUE)
            glfwWindowHint(GLFW_RESIZABLE, if (initialWindowSettings.resizable) GLFW_TRUE else GLFW_FALSE)
            glfwCreateWindow(initialWindowSettings.width, initialWindowSettings.height, initialWindowSettings.title, 0, 0)
        }
        is Fullscreen -> {
            val monitor = glfwGetPrimaryMonitor()
            glfwCreateWindow(initialWindowSettings.width, initialWindowSettings.height, initialWindowSettings.title, monitor, 0)
        }
    }

    glfwMakeContextCurrent(window)
    GL.createCapabilities()
    
    val game = Game<Context>()
    val inputController = when {
        path == null -> InputController(window)
        recording -> RecordingInputController(window, DataOutputStream(FileOutputStream(path)))
        !recording -> PlaybackInputController(window, DataInputStream(FileInputStream(path)))
        else -> throw AssertionError("Unreachable")
    }
    val context = initializer(game, inputController)
    inputController.lock()

    var alpha = 0.0
    var lastTime = glfwGetTime()

    while (game.state != null) {
        game.state?.render(context, alpha.toFloat())

        glfwSwapBuffers(window)
        glfwPollEvents()

        val t = glfwGetTime()
        alpha += (t - lastTime) * ups * speed
        lastTime = t
        for (i in 0 until 5) {
            if (alpha <= 1 || game.state == null)
                break
            inputController.preTick()
            game.state?.tick(context)
            inputController.postTick()
            alpha--
        }

        if (alpha > 1)
            alpha = 1.0
    }
    
    inputController.close()
}

interface Updateable<in Context> {
    fun tick(context: Context)
    fun render(context: Context, alpha: Float)
}

class Delayer<in T>(val updateable: Updateable<T>, val ticksPerTick: Int) : Updateable<T> {
    var counter = 0
    
    override fun tick(context: T) {
        if (++counter == ticksPerTick) {
            updateable.tick(context)
            counter = 0
        }
    }

    override fun render(context: T, alpha: Float) {
        updateable.render(context, (counter + alpha) / ticksPerTick)
    }
}

sealed class WindowSettings(val title: String)
class WindowedFullscreen(title: String): WindowSettings(title)
class Maximised(title: String, val undecorated: Boolean = false): WindowSettings(title)
class Windowed(title: String, val width: Int, val height: Int, val resizable: Boolean = true, val undecorated: Boolean = false): WindowSettings(title)
class Fullscreen(title: String, val width: Int, val height: Int): WindowSettings(title)

private fun usererror(msg: String): Nothing {
    System.err.println(msg)
    System.exit(1)
    throw AssertionError("Unreachable")
}