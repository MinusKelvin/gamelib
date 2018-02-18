package minuskelvin.gamelib.gl

import minuskelvin.gamelib.math.vector.Vector2i
import org.lwjgl.opengl.GL11.*

class Texture2D {
    private val id = glGenTextures()
    
    private var size: Vector2i? = null
    
    init {
        bind()
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    }
    
    fun bind() = glBindTexture(GL_TEXTURE_2D, id)
    
    fun allocate(size: Vector2i) {
        bind()
        this.size = size
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, size.x, size.y, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0)
    }
    
    fun allocate(from: Image) {
        bind()
        this.size = from.size
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, from.size.x, from.size.y, 0, GL_RGBA, GL_UNSIGNED_BYTE, from.buffer)
    }
}

