package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector3f

fun translation(xyz: Vector3f) = translation(xyz.x, xyz.y, xyz.z)
fun translation(x: Float, y: Float, z: Float) = Matrix4f(
        m00 = 1f, m10 = 0f, m20 = 0f, m30 = x,
        m01 = 0f, m11 = 1f, m21 = 0f, m31 = y,
        m02 = 0f, m12 = 0f, m22 = 1f, m32 = z,
        m03 = 0f, m13 = 0f, m23 = 0f, m33 = 1f
)

infix fun Matrix4f.translate(vec: Vector3f) = this * translation(vec)

fun rotation(dir: Vector2f, axis: Vector3f) = rotation(dir.x, dir.y, axis.x, axis.y, axis.z)
fun rotation(cos: Float, sin: Float, axis: Vector3f) = rotation(cos, sin, axis.x, axis.y, axis.z)
fun rotation(dir: Vector2f, x: Float, y: Float, z: Float) = rotation(dir.x, dir.y, x, y, z)
fun rotation(cos: Float, sin: Float, x: Float, y: Float, z: Float) = Matrix4f(
        m00 = cos + x*x*(1f-cos),   m10 = x*y*(1f-cos) - z*sin, m20 = x*z*(1f-cos) + y*sin, m30 = 0f,
        m01 = x*y*(1f-cos) + z*sin, m11 = cos + y*y*(1f-cos),   m21 = y*z*(1f-cos) - x*sin, m31 = 0f,
        m02 = x*z*(1f-cos) - y*sin, m12 = y*z*(1f-cos) + x*sin, m22 = cos + z*z*(1f-cos),   m32 = 0f,
        m03 = 0f,                   m13 = 0f,                   m23 = 0f,                   m33 = 1f
)