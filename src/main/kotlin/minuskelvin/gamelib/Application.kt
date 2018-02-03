package minuskelvin.gamelib

import minuskelvin.gamelib.gl.RenderTarget
import minuskelvin.gamelib.input.InputHandler
import minuskelvin.gamelib.math.Vector2i
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.GL_COLOR
import org.lwjgl.opengl.GL11.GL_DEPTH
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL

/**
 * Class for managing the basic functions of the game.
 * 
 * Provides the game glfwWindow and a state machine.
 * 
 * Typical usage:
 * 
 * ```kotlin
 * Application(windowConfig).use { app ->
 *     initialize()
 *     app.state = initialState
 *     app.run()
 *     cleanup()
 * }
 * ```
 * 
 * @see Screen
 */
class Application(winconfig: WindowConfig) : AutoCloseable {
    private val glfwWindow: Long
    private val errorCB = GLFWErrorCallback.createThrow()
    
    val inputHandler: InputHandler
    val screen: RenderTarget

    var state: Screen? = null
        set(value) {
            val old = field
            old?.switchFrom(value)
            field = value
            value?.switchTo(old)
        }
    
    init {
        glfwSetErrorCallback(errorCB)
        glfwInit()
        
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        
        glfwWindow = when (winconfig) {
            is Windowed -> {
                if (winconfig.maximized && !winconfig.resizeable)
                    error("Can't make the window maximized and resizable")
                glfwWindowHint(GLFW_MAXIMIZED, winconfig.maximized.glfw)
                glfwWindowHint(GLFW_RESIZABLE, winconfig.resizeable.glfw)
                glfwWindowHint(GLFW_DECORATED, winconfig.decorated.glfw)
                
                glfwCreateWindow(winconfig.size.x, winconfig.size.y, winconfig.title, NULL, NULL)
            }
            is Fullscreen -> {
                glfwCreateWindow(winconfig.size.x, winconfig.size.y, winconfig.title, glfwGetPrimaryMonitor(), NULL)
            }
            is WindowedFullscreen -> {
                val monitor = glfwGetPrimaryMonitor()
                val mode = glfwGetVideoMode(monitor)
                
                glfwWindowHint(GLFW_RED_BITS, mode.redBits())
                glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits())
                glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits())
                glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate())
                
                glfwCreateWindow(mode.width(), mode.height(), winconfig.title, NULL, NULL)
            }
        }
        
        glfwMakeContextCurrent(glfwWindow)
        GL.createCapabilities()
        
        glBindVertexArray(glGenVertexArrays())
        
        inputHandler = InputHandler(this, glfwWindow)
        screen = object: RenderTarget {
            override fun bind() = glBindFramebuffer(GL_FRAMEBUFFER, 0)
            override fun clearColor(r: Float, g: Float, b: Float, a: Float) = stackPush().use {
                bind()
                glClearBufferfv(GL_COLOR, 0, it.floats(r, g, b, a))
            }
            override fun clearDepth(depth: Float) = stackPush().use {
                bind()
                glClearBufferfv(GL_DEPTH, 0, it.floats(1f))
            }
        }
    }

    /**
     * Runs the main loop. Finishes when `state` is `null`.
     */
    fun run() {
        var frametime = glfwGetTime()
        while (state != null) {
            val t = glfwGetTime()
            val delta =  t - frametime
            frametime = t
            
            state?.render(delta)
            
            glfwSwapBuffers(glfwWindow)
            inputHandler.pollInput()
        }
    }
    
    fun createActionInput() = inputHandler.createActionInput()
    fun createButtonInput() = inputHandler.createButtonInput()
    fun createAxisInput()   = inputHandler.createAxisInput()

    override fun close() {
        glfwTerminate()
    }
}

interface Screen {
    /**
     * Called every frame when the application should render itself.
     * @param delta The time between the current frame and the last one in seconds.
     */
    fun render(delta: Double)
    
    fun switchTo(previous: Screen?) {}
    fun switchFrom(next: Screen?) {}
    
    fun windowClose() {}
    fun windowResize(width: Int, height: Int) {}
}

sealed class WindowConfig

class Windowed(
        val size: Vector2i,
        val title: String,
        val maximized: Boolean = false,
        val resizeable: Boolean = true,
        val decorated: Boolean = true
) : WindowConfig()

class Fullscreen(
        val size: Vector2i,
        val title: String
) : WindowConfig()

class WindowedFullscreen(val title: String) : WindowConfig()

private val Boolean.glfw get() = if (this) GLFW_TRUE else GLFW_FALSE