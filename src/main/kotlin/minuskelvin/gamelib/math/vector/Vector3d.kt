package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector3d(val x: Double, val y: Double, val z: Double) {
    constructor(xy: Vector2d, z: Double): this(xy.x, xy.y, z)
    constructor(x: Double, yz: Vector2d): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3d) = Vector3d(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3d) = Vector3d(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3d) = Vector3d(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3d) = Vector3d(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Double) = Vector3d(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun div(rhs: Double) = Vector3d(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    operator fun unaryMinus() = Vector3d(-x, -y, -z)

    infix fun dot(rhs: Vector3d) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3d) = Vector3d(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )

    fun lerp(to: Vector3d, alpha: Double) = Vector3d(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha),
            z = z.lerp(to.z, alpha)
    )

    fun toFloat() = Vector3f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length
}

operator fun Double.times(rhs: Vector3d) = Vector3d(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z
)