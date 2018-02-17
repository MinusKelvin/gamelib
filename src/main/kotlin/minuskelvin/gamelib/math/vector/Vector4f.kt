package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector4f(val x: Float, val y: Float, val z: Float, val w: Float) {
    constructor(xy: Vector2f, z: Float, w: Float): this(xy.x, xy.y, z, w)
    constructor(x: Float, yz: Vector2f, w: Float): this(x, yz.x, yz.y, w)
    constructor(x: Float, y: Float, zw: Vector2f): this(x, y, zw.x, zw.y)
    
    constructor(xy: Vector2f, zw: Vector2f): this(xy.x, xy.y, zw.x, zw.y)
    
    constructor(xyz: Vector3f, w: Float): this(xyz.x, xyz.y, xyz.z, w)
    constructor(x: Float, yzw: Vector3f): this(x, yzw.x, yzw.y, yzw.z)
    
    operator fun plus(rhs: Vector4f) = Vector4f(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z,
            w = this.w + rhs.w
    )

    operator fun minus(rhs: Vector4f) = Vector4f(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z,
            w = this.w - rhs.w
    )

    operator fun times(rhs: Vector4f) = Vector4f(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z,
            w = this.w * rhs.w
    )

    operator fun div(rhs: Vector4f) = Vector4f(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z,
            w = this.w / rhs.w
    )

    operator fun times(rhs: Float) = Vector4f(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs,
            w = this.w * rhs
    )

    operator fun div(rhs: Float) = Vector4f(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs,
            w = this.w / rhs
    )

    operator fun unaryMinus() = Vector4f(-x, -y, -z, -w)

    infix fun dot(rhs: Vector4f) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z + this.w * rhs.w

    fun lerp(to: Vector4f, alpha: Float) = Vector4f(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha),
            z = z.lerp(to.z, alpha),
            w = w.lerp(to.w, alpha)
    )

    fun toDouble() = Vector4d(
            x = x.toDouble(),
            y = y.toDouble(),
            z = z.toDouble(),
            w = w.toDouble()
    )

    fun toInt() = Vector4i(
            x = x.toInt(),
            y = y.toInt(),
            z = z.toInt(),
            w = w.toInt()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length
}

operator fun Float.times(rhs: Vector4f) = Vector4f(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z,
        w = this * rhs.w
)