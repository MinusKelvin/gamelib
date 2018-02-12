package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector3f(val x: Float, val y: Float, val z: Float) {
    constructor(xy: Vector2f, z: Float): this(xy.x, xy.y, z)
    constructor(x: Float, yz: Vector2f): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3f) = Vector3f(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3f) = Vector3f(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3f) = Vector3f(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3f) = Vector3f(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Float) = Vector3f(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun div(rhs: Float) = Vector3f(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    operator fun unaryMinus() = Vector3f(-x, -y, -z)

    infix fun dot(rhs: Vector3f) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3f) = Vector3f(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )

    fun lerp(to: Vector3f, alpha: Float) = Vector3f(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha),
            z = z.lerp(to.z, alpha)
    )

    fun toDouble() = Vector3d(
            x = x.toDouble(),
            y = y.toDouble(),
            z = z.toDouble()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length
}

operator fun Float.times(rhs: Vector3f) = Vector3f(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z
)