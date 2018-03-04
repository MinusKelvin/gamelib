package minuskelvin.gamelib.graphics

import minuskelvin.gamelib.gl.ShaderProgram
import minuskelvin.gamelib.gl.VertexArray
import minuskelvin.gamelib.gl.VertexBuffer
import minuskelvin.gamelib.gl.VertexStruct
import minuskelvin.gamelib.math.rotate
import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector3f
import org.lwjgl.opengl.GL11.GL_TRIANGLES
import org.lwjgl.opengl.GL11.glDrawArrays
import org.lwjgl.opengl.GL15.GL_STREAM_DRAW

class ShapeRenderer : AutoCloseable {
    private val array = VertexArray(ShapeVertexStruct, 1024)
    private val buffer = VertexBuffer(ShapeVertexStruct)
    
    private var inUse = false
    
    var shader = defaultShader
    
    fun start(camera: Camera): ShapeRendererContext {
        if (inUse) throw IllegalStateException("This ShapeRenderer is already in use!")
        return ShapeRendererContext(camera)
    }
    
    inline fun draw(camera: Camera, block: (ShapeRendererContext) -> Unit) {
        start(camera).use { block(it) }
    }
    
    override fun close() {
        array.close()
        buffer.close()
    }
    
    inner class ShapeRendererContext(val camera: Camera) : AutoCloseable {
        private var index = 0
        
        var defaultColor = Color.WHITE
        
        private inline fun vertex(b: ShapeVertexStruct.() -> Unit) {
            if (index >= array.length)
                array.realloc(array.length*2)
            array[index++].b()
        }

        fun rectangle(cx: Float, cy: Float, hw: Float, hh: Float, color: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(cx,  cy,  hw, hh,     color, color, color, color, cos, sin,     z)
        fun rectangle(c: Vector2f,          hs: Vector2f,         color: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(c.x, c.y, hs.x, hs.y, color, color, color, color, rot.x, rot.y, z)
        fun rectangle(cx: Float, cy: Float, hs: Vector2f,         color: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(cx,  cy,  hs.x, hs.y, color, color, color, color, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hw: Float, hh: Float, color: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(c.x, c.y, hw, hh,     color, color, color, color, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hs: Vector2f,         color: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(c.x, c.y, hs.x, hs.y, color, color, color, color, cos, sin,     z)
        fun rectangle(cx: Float, cy: Float, hw: Float, hh: Float, color: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(cx,  cy,  hw, hh,     color, color, color, color, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hw: Float, hh: Float, color: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(c.x, c.y, hw, hh,     color, color, color, color, cos, sin,     z)
        fun rectangle(cx: Float, cy: Float, hs: Vector2f,         color: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(cx,  cy,  hs.x, hs.y, color, color, color, color, cos, sin,     z)
        
        fun rectangle(c: Vector2f,          hs: Vector2f,         coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(c.x, c.y, hs.x, hs.y, coltl, coltr, colbl, colbr, rot.x, rot.y, z)
        fun rectangle(cx: Float, cy: Float, hs: Vector2f,         coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(cx,  cy,  hs.x, hs.y, coltl, coltr, colbl, colbr, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hw: Float, hh: Float, coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(c.x, c.y, hw,   hh,   coltl, coltr, colbl, colbr, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hs: Vector2f,         coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(c.x, c.y, hs.x, hs.y, coltl, coltr, colbl, colbr, cos,   sin,   z)
        fun rectangle(cx: Float, cy: Float, hw: Float, hh: Float, coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, rot: Vector2f,                    z: Float = 0f) = rectangle(cx,  cy,  hw,   hh,   coltl, coltr, colbl, colbr, rot.x, rot.y, z)
        fun rectangle(c: Vector2f,          hw: Float, hh: Float, coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(c.x, c.y, hw,   hh,   coltl, coltr, colbl, colbr, cos,   sin,   z)
        fun rectangle(cx: Float, cy: Float, hs: Vector2f,         coltl: Color = defaultColor, coltr: Color = defaultColor, colbl: Color = defaultColor, colbr: Color = defaultColor, cos: Float = 1f, sin: Float = 0f, z: Float = 0f) = rectangle(cx,  cy,  hs.x, hs.y, coltl, coltr, colbl, colbr, cos,   sin,   z)

        /**
         * Draws a rectangle at `c` (or `[cx, cy]`) with half-size `hs` (or `[hw, hh]`)
         * and rotation specified by the unit vector `rot` (or `[cos, sin]`) with the specified corner colors.
         */
        fun rectangle(cx: Float, cy: Float, hw: Float, hh: Float,
                      coltl: Color = defaultColor, coltr: Color = defaultColor,
                      colbl: Color = defaultColor, colbr: Color = defaultColor,
                      cos: Float = 1f, sin: Float = 0f, z: Float = 0f) {
            vertex {
                pos = Vector3f(rotate(Vector2f(-hw,  hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = coltl
            }
            vertex {
                pos = Vector3f(rotate(Vector2f(-hw, -hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = colbl
            }
            vertex {
                pos = Vector3f(rotate(Vector2f( hw,  hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = coltr
            }
            vertex {
                pos = Vector3f(rotate(Vector2f( hw,  hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = coltr
            }
            vertex {
                pos = Vector3f(rotate(Vector2f(-hw, -hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = colbl
            }
            vertex {
                pos = Vector3f(rotate(Vector2f( hw, -hh), Vector2f(cos, sin)) + Vector2f(cx, cy), z)
                color = colbr
            }
        }
        
        override fun close() {
            inUse = false
            buffer.allocate(array, GL_STREAM_DRAW)
            shader.use(camera)
            buffer.bindVertexLayout()
            glDrawArrays(GL_TRIANGLES, 0, index)
        }
    }
    
    companion object {
        val defaultShader by lazy { ShaderProgram(
                """
                    #version 330 core
                    
                    in layout(location = 0) vec3 pos;
                    in layout(location = 1) vec4 color;
                    
                    uniform mat4 camera;
                    
                    out vec4 c;
                    
                    void main() {
                        gl_Position = camera * vec4(pos, 1.0);
                        c = color;
                    }
                """.trimIndent(),
                """
                    #version 330 core
                    
                    in vec4 c;
                    
                    out vec4 color;
                    
                    void main() {
                        color = c;
                    }
                """.trimIndent(),
                "camera"
        ) }
    }
}

private object ShapeVertexStruct : VertexStruct<ShapeVertexStruct>() {
    var pos by Vector3fAttribute(0)
    var color by ColorAttribute(1)
}