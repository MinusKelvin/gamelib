package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.lerp
import kotlin.math.sqrt

data class Vector2f(val x: Float, val y: Float) {
    operator fun plus(rhs: Vector2f) = Vector2f(
            x = this.x + rhs.x,
            y = this.y + rhs.y
    )

    operator fun minus(rhs: Vector2f) = Vector2f(
            x = this.x - rhs.x,
            y = this.y - rhs.y
    )

    operator fun times(rhs: Vector2f) = Vector2f(
            x = this.x * rhs.x,
            y = this.y * rhs.y
    )

    operator fun div(rhs: Vector2f) = Vector2f(
            x = this.x / rhs.x,
            y = this.y / rhs.y
    )

    operator fun times(rhs: Float) = Vector2f(
            x = this.x * rhs,
            y = this.y * rhs
    )

    operator fun div(rhs: Float) = Vector2f(
            x = this.x / rhs,
            y = this.y / rhs
    )
    
    operator fun unaryMinus() = Vector2f(-x, -y)
    
    infix fun dot(rhs: Vector2f) = this.x * rhs.x + this.y * rhs.y

    fun lerp(to: Vector2f, alpha: Float) = Vector2f(
            x = x.lerp(to.x, alpha),
            y = y.lerp(to.y, alpha)
    )

    fun toDouble() = Vector2d(
            x = x.toDouble(),
            y = y.toDouble()
    )

    fun toInt() = Vector2i(
            x = x.toInt(),
            y = y.toInt()
    )
    
    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)
    
    val unit get() = this / length
}

operator fun Float.times(rhs: Vector2f) = Vector2f(
        x = this * rhs.x,
        y = this * rhs.y
)