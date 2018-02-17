package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector2d(val x: Double, val y: Double) {
    operator fun plus(rhs: Vector2d) = Vector2d(
            x = this.x + rhs.x,
            y = this.y + rhs.y
    )

    operator fun minus(rhs: Vector2d) = Vector2d(
            x = this.x - rhs.x,
            y = this.y - rhs.y
    )

    operator fun times(rhs: Vector2d) = Vector2d(
            x = this.x * rhs.x,
            y = this.y * rhs.y
    )

    operator fun div(rhs: Vector2d) = Vector2d(
            x = this.x / rhs.x,
            y = this.y / rhs.y
    )

    operator fun times(rhs: Double) = Vector2d(
            x = this.x * rhs,
            y = this.y * rhs
    )

    operator fun div(rhs: Double) = Vector2d(
            x = this.x / rhs,
            y = this.y / rhs
    )

    operator fun unaryMinus() = Vector2d(-x, -y)
    
    infix fun dot(rhs: Vector2d) = this.x * rhs.x + this.y * rhs.y
    
    fun lerp(to: Vector2d, alpha: Double) = Vector2d(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha)
    )

    fun toFloat() = Vector2f(
            x = x.toFloat(),
            y = y.toFloat()
    )

    fun toInt() = Vector2i(
            x = x.toInt(),
            y = y.toInt()
    )
    
    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)
    
    val unit get() = this / length
}

operator fun Float.times(rhs: Vector2d) = Vector2d(
        x = this * rhs.x,
        y = this * rhs.y
)