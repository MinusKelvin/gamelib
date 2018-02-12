package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector3i(val x: Int, val y: Int, val z: Int) {
    constructor(xy: Vector2i, z: Int): this(xy.x, xy.y, z)
    constructor(x: Int, yz: Vector2i): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3i) = Vector3i(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3i) = Vector3i(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3i) = Vector3i(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3i) = Vector3i(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Int) = Vector3i(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun div(rhs: Int) = Vector3i(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    operator fun unaryMinus() = Vector3i(-x, -y, -z)

    infix fun dot(rhs: Vector3i) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3i) = Vector3i(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )

    fun lerp(to: Vector3i, alpha: Double) = Vector3i(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha),
            z = z.lerp(to.z, alpha)
    )
    
    fun toDouble() = Vector3d(
            x = x.toDouble(),
            y = y.toDouble(),
            z = z.toDouble()
    )

    fun toFloat() = Vector3f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq.toDouble())
}

operator fun Int.times(rhs: Vector3i) = Vector3i(
        x = this * rhs.x,
        y = this * rhs.y,
        z = this * rhs.z
)