package minuskelvin.gamelib.test

import minuskelvin.gamelib.Application
import minuskelvin.gamelib.Screen
import minuskelvin.gamelib.Windowed
import minuskelvin.gamelib.graphics.Camera
import minuskelvin.gamelib.graphics.Color
import minuskelvin.gamelib.graphics.Orthographic
import minuskelvin.gamelib.graphics.ShapeRenderer
import minuskelvin.gamelib.math.rotate
import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector2i
import org.lwjgl.opengl.GL11.*
import kotlin.math.cos
import kotlin.math.sin

class State(val app: Application) : Screen {
    val renderer = ShapeRenderer()
    val projection = Orthographic(-4/3f, 4/3f, -1f, 1f, 0f, 2f)
    val camera = Camera(projection)
    
    var rot = Vector2f(1f, 0f)
    val rotby = Vector2f(cos(0.01f), sin(0.01f))
    
    init {
        projection.zoom = -1f
    }
    
    override fun render(delta: Double) {
        app.screen.clearColor(0f, 0f, 0f, 1f)
        
        glEnable(GL_CULL_FACE)
        glCullFace(GL_BACK)
        glFrontFace(GL_CCW)
        
        renderer.draw(camera) { drawer ->
            drawer.rectangle(0f, 0f, 1.2f, 0.8f, rot = rot,
                    coltl = Color.RED, coltr = Color.GREEN,
                    colbl = Color.BLUE, colbr = Color.WHITE)
        }
        
        rot = rotate(rot, rotby)
        
        app.inputHandler.tick()
    }

    override fun windowClose() {
        app.state = null
    }

    override fun windowResize(width: Int, height: Int) {
        projection.left = -width.toFloat() / height
        projection.right = width.toFloat() / height
    }
}

fun main(args: Array<String>) {
    Application(Windowed(Vector2i(800, 600), "Minimum")).use { app ->
        app.state = State(app)
        app.run()
    }
}
