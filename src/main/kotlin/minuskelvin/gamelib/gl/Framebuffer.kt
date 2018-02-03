package minuskelvin.gamelib.gl

interface RenderTarget {
    fun bind()
    fun clearColor(r: Float, g: Float, b: Float, a: Float)
    fun clearDepth(depth: Float)
}

class Framebuffer // TODO