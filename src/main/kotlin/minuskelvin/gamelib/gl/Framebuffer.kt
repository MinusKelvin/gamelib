package minuskelvin.gamelib.gl

import org.lwjgl.opengl.GL11.GL_COLOR
import org.lwjgl.opengl.GL11.GL_DEPTH
import org.lwjgl.opengl.GL20.glDrawBuffers
import org.lwjgl.opengl.GL30.*
import org.lwjgl.opengl.GL32.glFramebufferTexture
import org.lwjgl.system.MemoryStack

interface RenderTarget {
    fun bind()
    
    fun clearColor(r: Float, g: Float, b: Float, a: Float) = MemoryStack.stackPush().use {
        bind()
        glClearBufferfv(GL_COLOR, 0, it.floats(r, g, b, a))
    }
    
    fun clearDepth(depth: Float) = MemoryStack.stackPush().use {
        bind()
        glClearBufferfv(GL_DEPTH, 0, it.floats(1f))
    }
}

class Framebuffer : RenderTarget, AutoCloseable {
    val id = glGenFramebuffers()

    override fun bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id)
    }
    
    fun attachColor(texture: Texture2D, n: Int = 0) {
        bind()
        glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0 + n, texture.id, 0)
    }
    
    fun attachDepth(texture: DepthTexture) {
        bind()
        glFramebufferTexture(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, texture.id, 0)
    }
    
    fun useColorAttachments(attachments: IntArray) {
        bind()
        glDrawBuffers(attachments)
    }

    override fun close() {
        glDeleteFramebuffers(id)
    }
}