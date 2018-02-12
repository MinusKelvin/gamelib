package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector4d(val x: Double, val y: Double, val z: Double, val w: Double) {
    constructor(xy: Vector2d, z: Double, w: Double): this(xy.x, xy.y, z, w)
    constructor(x: Double, yz: Vector2d, w: Double): this(x, yz.x, yz.y, w)
    constructor(x: Double, y: Double, zw: Vector2d): this(x, y, zw.x, zw.y)
    
    constructor(xy: Vector2d, zw: Vector2d): this(xy.x, xy.y, zw.x, zw.y)
    
    constructor(xyz: Vector3d, w: Double): this(xyz.x, xyz.y, xyz.z, w)
    constructor(x: Double, yzw: Vector3d): this(x, yzw.x, yzw.y, yzw.z)
    
    operator fun plus(rhs: Vector4d) = Vector4d(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z,
            w = this.w + rhs.w
    )

    operator fun minus(rhs: Vector4d) = Vector4d(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z,
            w = this.w - rhs.w
    )

    operator fun times(rhs: Vector4d) = Vector4d(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z,
            w = this.w * rhs.w
    )

    operator fun div(rhs: Vector4d) = Vector4d(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z,
            w = this.w / rhs.w
    )

    operator fun times(rhs: Double) = Vector4d(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs,
            w = this.w * rhs
    )

    operator fun div(rhs: Double) = Vector4d(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs,
            w = this.w / rhs
    )

    operator fun unaryMinus() = Vector4d(-x, -y, -z, -w)

    infix fun dot(rhs: Vector4d) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z + this.w * rhs.w

    fun lerp(to: Vector4d, alpha: Double) = Vector4d(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha),
            z = z.lerp(to.z, alpha),
            w = w.lerp(to.w, alpha)
    )

    fun toFloat() = Vector4f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat(),
            w = w.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length
}

operator fun Double.times(rhs: Vector4d) = Vector4d(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z,
        w = this * rhs.w
)