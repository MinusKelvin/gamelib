package minuskelvin.gamelib.test

import minuskelvin.gamelib.Application
import minuskelvin.gamelib.Screen
import minuskelvin.gamelib.Windowed
import minuskelvin.gamelib.gl.ShaderProgram
import minuskelvin.gamelib.gl.VertexArray
import minuskelvin.gamelib.gl.VertexBuffer
import minuskelvin.gamelib.gl.VertexStruct
import minuskelvin.gamelib.math.Vector2i
import minuskelvin.gamelib.math.Vector3f
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.GL_STATIC_DRAW
import org.lwjgl.opengl.GL20.glEnableVertexAttribArray
import org.lwjgl.opengl.GL20.glVertexAttribPointer
import java.util.*

object PosStruct : VertexStruct<PosStruct>() {
    var pos by Vector3fAttribute()
}

class State(val app: Application) : Screen {
    val buffer = VertexBuffer(PosStruct)
    val shader = ShaderProgram(
            Scanner(State::class.java.getResourceAsStream("/vertex.glsl")).use { it.useDelimiter("\\Z").next() },
            Scanner(State::class.java.getResourceAsStream("/fragment.glsl")).use { it.useDelimiter("\\Z").next() }
    )
    
    init {
        VertexArray(PosStruct, 3).use {
            it[0].pos = Vector3f(-1f, -1f, 0f)
            it[1].pos = Vector3f(0f, 1f, 0f)
            it[2].pos = Vector3f(1f, -1f, 0f)
            buffer.allocate(it, GL_STATIC_DRAW)
        }
    }
    
    override fun render(delta: Double) {
        app.screen.clearColor(0.5f, 0f, 0f, 1f)

        shader.use()
        buffer.bind()
        glVertexAttribPointer(0, 3, GL_FLOAT, false, PosStruct.size, 0)
        glEnableVertexAttribArray(0)
        
        glDrawArrays(GL_TRIANGLES, 0, 3)
        
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
