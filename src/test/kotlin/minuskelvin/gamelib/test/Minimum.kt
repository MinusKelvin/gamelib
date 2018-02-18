package minuskelvin.gamelib.test

import minuskelvin.gamelib.Application
import minuskelvin.gamelib.Screen
import minuskelvin.gamelib.Windowed
import minuskelvin.gamelib.gl.*
import minuskelvin.gamelib.graphics.Color
import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector2i
import minuskelvin.gamelib.math.vector.Vector3f
import minuskelvin.gamelib.math.vector.Vector4f
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.GL_STATIC_DRAW
import java.util.*

object PosColorStruct : VertexStruct<PosColorStruct>() {
    var pos by Vector3fAttribute(0)
    var color by ByteVector4fAttribute(1)
    var tex by ShortVector2fAttribute(2)
}

class State(val app: Application) : Screen {
    val buffer = VertexBuffer(PosColorStruct)
    val shader = ShaderProgram(
            Scanner(State::class.java.getResourceAsStream("/vertex.glsl")).use { it.useDelimiter("\\Z").next() },
            Scanner(State::class.java.getResourceAsStream("/fragment.glsl")).use { it.useDelimiter("\\Z").next() }
    )
    val texture = Texture2D()
    
    init {
        VertexArray(PosColorStruct, 3).use {
            it[0].pos = Vector3f(-0.9f, -0.9f, 0f)
            it[0].color = Vector4f(1f, 0.5f, 0.5f, 1f)
            it[0].tex = Vector2f(0f, 1f)
            it[1].pos = Vector3f(0f, 0.9f, 0f)
            it[1].color = Vector4f(0.5f, 1f, 0.5f, 1f)
            it[1].tex = Vector2f(0.5f, 0f)
            it[2].pos = Vector3f(0.9f, -0.9f, 0f)
            it[2].color = Vector4f(0.5f, 0.5f, 1f, 1f)
            it[2].tex = Vector2f(1f, 1f)
            buffer.allocate(it, GL_STATIC_DRAW)
        }
        
        STBImage(State::class.java.getResourceAsStream("/texture.png")).use {
            it[32,30] = Color(0.5f, 0.5f, 0.5f, 1f)
            texture.allocate(it)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        }
    }
    
    override fun render(delta: Double) {
        app.screen.clearColor(0f, 0f, 0f, 1f)

        shader.use()
        buffer.bindVertexLayout().use {
            glDrawArrays(GL_TRIANGLES, 0, 3)
        }
        
        app.inputHandler.tick()
    }

    override fun windowClose() {
        app.state = null
    }
}

fun main(args: Array<String>) {
    Application(Windowed(Vector2i(800, 600), "Minimum")).use { app ->
        app.state = State(app)
        app.run()
    }
}
