package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector2i(val x: Int, val y: Int) {
    operator fun plus(rhs: Vector2i) = Vector2i(
            x = this.x + rhs.x,
            y = this.y + rhs.y
    )

    operator fun minus(rhs: Vector2i) = Vector2i(
            x = this.x - rhs.x,
            y = this.y - rhs.y
    )

    operator fun times(rhs: Vector2i) = Vector2i(
            x = this.x * rhs.x,
            y = this.y * rhs.y
    )

    operator fun div(rhs: Vector2i) = Vector2i(
            x = this.x / rhs.x,
            y = this.y / rhs.y
    )

    operator fun times(rhs: Int) = Vector2i(
            x = this.x * rhs,
            y = this.y * rhs
    )

    operator fun div(rhs: Int) = Vector2i(
            x = this.x / rhs,
            y = this.y / rhs
    )

    operator fun unaryMinus() = Vector2i(-x, -y)

    fun lerp(to: Vector2i, alpha: Double) = Vector2i(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha)
    )
    
    infix fun dot(rhs: Vector2i) = this.x * rhs.x + this.y * rhs.y

    fun toDouble() = Vector2d(
            x = x.toDouble(),
            y = y.toDouble()
    )

    fun toFloat() = Vector2f(
            x = x.toFloat(),
            y = y.toFloat()
    )
    
    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq.toDouble())
}

operator fun Int.times(rhs: Vector2i) = Vector2i(
        x = this * rhs.x,
        y = this * rhs.y
)