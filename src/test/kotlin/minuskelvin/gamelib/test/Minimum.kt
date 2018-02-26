package minuskelvin.gamelib.test

import minuskelvin.gamelib.Application
import minuskelvin.gamelib.Screen
import minuskelvin.gamelib.Windowed
import minuskelvin.gamelib.gl.*
import minuskelvin.gamelib.graphics.Camera
import minuskelvin.gamelib.graphics.Color
import minuskelvin.gamelib.graphics.Perspective
import minuskelvin.gamelib.input.ButtonOrAxisSource
import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector2i
import minuskelvin.gamelib.math.vector.Vector3f
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.GL_STATIC_DRAW
import java.util.*
import kotlin.math.PI

object PosColorStruct : VertexStruct<PosColorStruct>() {
    var pos by Vector3fAttribute(0)
    var tex by ShortVector2fAttribute(1)
}

class State(val app: Application) : Screen {
    val buffer = VertexBuffer(PosColorStruct)
    val shader = ShaderProgram(
            Scanner(State::class.java.getResourceAsStream("/vertex.glsl")).use { it.useDelimiter("\\Z").next() },
            Scanner(State::class.java.getResourceAsStream("/fragment.glsl")).use { it.useDelimiter("\\Z").next() }
    )
    val texture = Texture2D()
    val projection =
            Perspective(PI.toFloat()/2, 4/3f, 0.001f, 100f)
//            Orthographic(-4/3f, 4/3f, -1f, 1f, 0f, 2f)
    val camera = Camera(projection)
    val cameraloc = shader.getUniformLocation("proj")
    
    val xplus = app.inputHandler.createAxisInput()
    val xminus = app.inputHandler.createAxisInput()
    val yplus = app.inputHandler.createAxisInput()
    val yminus = app.inputHandler.createAxisInput()

    val yawplus = app.inputHandler.createAxisInput()
    val yawminus = app.inputHandler.createAxisInput()
    val pitchplus = app.inputHandler.createAxisInput()
    val pitchminus = app.inputHandler.createAxisInput()
    val rollplus = app.inputHandler.createAxisInput()
    val rollminus = app.inputHandler.createAxisInput()
    
    init {
        VertexArray(PosColorStruct, 12).use {
            it[0].pos = Vector3f(-1f, -1f, -1f)
            it[0].tex = Vector2f(0f, 1f)
            it[1].pos = Vector3f(-1f, 1f, -1f)
            it[1].tex = Vector2f(0f, 0f)
            it[2].pos = Vector3f(1f, 1f, -1f)
            it[2].tex = Vector2f(1f, 0f)
            it[3].pos = Vector3f(-1f, -1f, -1f)
            it[3].tex = Vector2f(0f, 1f)
            it[4].pos = Vector3f(1f, 1f, -1f)
            it[4].tex = Vector2f(1f, 0f)
            it[5].pos = Vector3f(1f, -1f, -1f)
            it[5].tex = Vector2f(1f, 1f)

            it[6].pos = Vector3f(-1f, -1f, 1f)
            it[6].tex = Vector2f(0f, 1f)
            it[7].pos = Vector3f(-1f, 1f, 1f)
            it[7].tex = Vector2f(0f, 0f)
            it[8].pos = Vector3f(1f, 1f, 1f)
            it[8].tex = Vector2f(1f, 0f)
            it[9].pos = Vector3f(-1f, -1f, 1f)
            it[9].tex = Vector2f(0f, 1f)
            it[10].pos = Vector3f(1f, 1f, 1f)
            it[10].tex = Vector2f(1f, 0f)
            it[11].pos = Vector3f(1f, -1f, 1f)
            it[11].tex = Vector2f(1f, 1f)
            buffer.allocate(it, GL_STATIC_DRAW)
        }
        
        STBImage(State::class.java.getResourceAsStream("/texture.png")).use {
            it[32,30] = Color(0.5f, 0.5f, 0.5f, 1f)
            texture.allocate(it)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        }

        yawplus.bind(ButtonOrAxisSource.CONTROLLER_RS_X_PLUS)
        yawminus.bind(ButtonOrAxisSource.CONTROLLER_RS_X_MINUS)
        pitchplus.bind(ButtonOrAxisSource.CONTROLLER_RS_Y_PLUS)
        pitchminus.bind(ButtonOrAxisSource.CONTROLLER_RS_Y_MINUS)
        rollplus.bind(ButtonOrAxisSource.CONTROLLER_RIGHT_TRIGGER)
        rollminus.bind(ButtonOrAxisSource.CONTROLLER_LEFT_TRIGGER)
    }
    
    override fun render(delta: Double) {
        app.screen.clearColor(0f, 0f, 0f, 1f)

        camera.yaw += (yawplus.value - yawminus.value) / 10f
        camera.pitch += (pitchplus.value - pitchminus.value) / 10f
        camera.roll = (rollplus.value - rollminus.value) * PI.toFloat() / 2
        
        shader.use()
        camera.use(cameraloc)
        buffer.bindVertexLayout().use {
            glDrawArrays(GL_TRIANGLES, 0, 12)
        }
        
        app.inputHandler.tick()
    }

    override fun windowClose() {
        app.state = null
    }

    override fun windowResize(width: Int, height: Int) {
        projection.aspect = width.toFloat() / height
//        projection.left = -width.toFloat() / height
//        projection.right = width.toFloat() / height
    }
}

fun main(args: Array<String>) {
    Application(Windowed(Vector2i(800, 600), "Minimum")).use { app ->
        app.state = State(app)
        app.run()
    }
}
