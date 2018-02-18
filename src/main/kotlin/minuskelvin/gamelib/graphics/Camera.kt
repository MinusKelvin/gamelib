package minuskelvin.gamelib.graphics

import minuskelvin.gamelib.math.matrix.Matrix4f
import minuskelvin.gamelib.math.matrix.putMatrix
import minuskelvin.gamelib.math.matrix.rotation
import minuskelvin.gamelib.math.matrix.translation
import minuskelvin.gamelib.math.vector.Vector3f
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryStack.stackPush
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class Camera(val projection: Projection) {
    private var dirty = false
    
    var position = Vector3f(0f, 0f, 0f); set(v) { field = v; dirty = true }
    var yaw = 0f;                        set(v) { field = v; dirty = true }
    var pitch = 0f;                      set(v) { field = v; dirty = true }
    var roll = 0f;                       set(v) { field = v; dirty = true }
    
    private var matrix = genMatrix()
    
    fun use(uniformLocation: Int) {
        if (dirty) matrix = genMatrix()
        stackPush().use { stack ->
            val buf = stack.mallocFloat(16)
            buf.putMatrix(0, matrix)
            GL20.glUniformMatrix4fv(uniformLocation, false, buf)
        }
    }
    
    private fun genMatrix() = projection.getMatrix() *
                rotation(cos(roll), sin(roll), 0f, 0f, -1f) * // roll
                rotation(cos(pitch), sin(pitch), 1f, 0f, 0f) * // pitch
                rotation(cos(yaw), sin(yaw), 0f, 1f, 0f) * // yaw
                translation(-position.x, -position.y, -position.z)
}

sealed class Projection {
    internal abstract fun getMatrix(): Matrix4f
}

class Orthographic(left: Float, right: Float, bottom: Float, top: Float, near: Float = -1f, far: Float = 1f) : Projection() {
    private var dirty = false
    var left = left;     set(v) { field = v; dirty = true }
    var right = right;   set(v) { field = v; dirty = true }
    var bottom = bottom; set(v) { field = v; dirty = true }
    var top = top;       set(v) { field = v; dirty = true }
    var near = near;     set(v) { field = v; dirty = true }
    var far = far;       set(v) { field = v; dirty = true }
    
    private var matrix = genMatrix()
    
    override fun getMatrix(): Matrix4f {
        if (dirty)
            matrix = genMatrix()
        return matrix
    }
    
    private fun genMatrix() = Matrix4f(
            m00 = 2f / (right - left), m10 = 0f,                  m20 = 0f,                m30 = -(left + right) / 2f,
            m01 = 0f,                  m11 = 2f / (top - bottom), m21 = 0f,                m31 = -(top + bottom) / 2f,
            m02 = 0f,                  m12 = 0f,                  m22 = 2f / (far - near), m32 = -(near + far) / 2f,
            m03 = 0f,                  m13 = 0f,                  m23 = 0f,                m33 = 1f
    )
}

class Perspective(fov: Float, aspect: Float, near: Float, far: Float) : Projection() {
    var dirty = false
    var fov = fov;       set(v) { field = v; dirty = true }
    var aspect = aspect; set(v) { field = v; dirty = true }
    var near = near;     set(v) { field = v; dirty = true }
    var far = far;       set(v) { field = v; dirty = true }

    private var matrix = genMatrix()

    override fun getMatrix(): Matrix4f {
        if (dirty)
            matrix = genMatrix()
        return matrix
    }

    private fun genMatrix(): Matrix4f {
        val tan = tan(fov / 2f)
        return Matrix4f(
                m00 = 1f / (tan * aspect), m10 = 0f,       m20 = 0f,                          m30 = 0f,
                m01 = 0f,                  m11 = 1f / tan, m21 = 0f,                          m31 = 0f,
                m02 = 0f,                  m12 = 0f,       m22 = (near + far) / (near - far), m32 = far * 2 * near / (near - far),
                m03 = 0f,                  m13 = 0f,       m23 = -1f,                         m33 = 1f
        )
    }
}