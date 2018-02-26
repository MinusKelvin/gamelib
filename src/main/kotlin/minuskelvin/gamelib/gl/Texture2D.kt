package minuskelvin.gamelib.gl

import minuskelvin.gamelib.math.vector.Vector2i
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.GL_DEPTH_COMPONENT32F

class Texture2D : AutoCloseable {
    val id = glGenTextures()
    
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
    
    fun update(from: Image, topleft: Vector2i) {
        bind()
        glTexSubImage2D(GL_TEXTURE_2D, 0, topleft.x, topleft.y, from.size.x, from.size.y, GL_RGBA, GL_UNSIGNED_BYTE, from.buffer)
    }

    override fun close() {
        glDeleteTextures(id)
    }
}

class DepthTexture : AutoCloseable {
    val id = glGenTextures()
    
    private var size: Vector2i? = null
    
    init {
        bind()
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    }
    
    fun bind() = glBindTexture(GL_TEXTURE_2D, id)
    
    fun allocate(size: Vector2i) {
        bind()
        this.size = size
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT32F, size.x, size.y, 0, GL_DEPTH_COMPONENT, GL_UNSIGNED_BYTE, 0)
    }

    override fun close() {
        glDeleteTextures(id)
    }
}
