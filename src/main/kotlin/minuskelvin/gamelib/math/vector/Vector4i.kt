package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector4i(val x: Int, val y: Int, val z: Int, val w: Int) {
    constructor(xy: Vector2i, z: Int, w: Int): this(xy.x, xy.y, z, w)
    constructor(x: Int, yz: Vector2i, w: Int): this(x, yz.x, yz.y, w)
    constructor(x: Int, y: Int, zw: Vector2i): this(x, y, zw.x, zw.y)
    
    constructor(xy: Vector2i, zw: Vector2i): this(xy.x, xy.y, zw.x, zw.y)
    
    constructor(xyz: Vector3i, w: Int): this(xyz.x, xyz.y, xyz.z, w)
    constructor(x: Int, yzw: Vector3i): this(x, yzw.x, yzw.y, yzw.z)
    
    operator fun plus(rhs: Vector4i) = Vector4i(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z,
            w = this.w + rhs.w
    )

    operator fun minus(rhs: Vector4i) = Vector4i(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z,
            w = this.w - rhs.w
    )

    operator fun times(rhs: Vector4i) = Vector4i(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z,
            w = this.w * rhs.w
    )

    operator fun div(rhs: Vector4i) = Vector4i(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z,
            w = this.w / rhs.w
    )

    operator fun times(rhs: Int) = Vector4i(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs,
            w = this.w * rhs
    )

    operator fun div(rhs: Int) = Vector4i(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs,
            w = this.w / rhs
    )

    operator fun unaryMinus() = Vector4i(-x, -y, -z, -w)

    infix fun dot(rhs: Vector4i) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z + this.w * rhs.w

    fun lerp(to: Vector4i, alpha: Double) = Vector4i(
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

    fun toFloat() = Vector4f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat(),
            w = w.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq.toDouble())
}

operator fun Int.times(rhs: Vector4i) = Vector4i(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z,
        w = this * rhs.w
)