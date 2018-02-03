package minuskelvin.gamelib.math

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
    
    operator fun Float.times(rhs: Vector2f) = Vector2f(
            x = this * rhs.x,
            y = this * rhs.y
    )

    operator fun div(rhs: Float) = Vector2f(
            x = this.x / rhs,
            y = this.y / rhs
    )
    
    infix fun dot(rhs: Vector2f) = this.x * rhs.x + this.y * rhs.y

    fun lerp(to: Vector2f, alpha: Float) = Vector2f(
            x = (1 - alpha) * x + alpha * to.x,
            y = (1 - alpha) * y + alpha * to.y
    )

    fun toDouble() = Vector2d(
            x = x.toDouble(),
            y = y.toDouble()
    )
    
    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)
    
    val unit get() = this / length
    
    val xx get() = Vector2f(x, x)
    val yx get() = Vector2f(y, x)
    val xy get() = this
    val yy get() = Vector2f(y, y)

    val xxx get() = Vector3f(x, x, x)
    val yxx get() = Vector3f(y, x, x)
    val xyx get() = Vector3f(x, y, x)
    val yyx get() = Vector3f(y, y, x)
    val xxy get() = Vector3f(x, x, y)
    val yxy get() = Vector3f(y, x, y)
    val xyy get() = Vector3f(x, y, y)
    val yyy get() = Vector3f(y, y, y)

    val xxxx get() = Vector4f(x, x, x, x)
    val yxxx get() = Vector4f(y, x, x, x)
    val xyxx get() = Vector4f(x, y, x, x)
    val yyxx get() = Vector4f(y, y, x, x)
    val xxyx get() = Vector4f(x, x, y, x)
    val yxyx get() = Vector4f(y, x, y, x)
    val xyyx get() = Vector4f(x, y, y, x)
    val yyyx get() = Vector4f(y, y, y, x)
    val xxxy get() = Vector4f(x, x, x, y)
    val yxxy get() = Vector4f(y, x, x, y)
    val xyxy get() = Vector4f(x, y, x, y)
    val yyxy get() = Vector4f(y, y, x, y)
    val xxyy get() = Vector4f(x, x, y, y)
    val yxyy get() = Vector4f(y, x, y, y)
    val xyyy get() = Vector4f(x, y, y, y)
    val yyyy get() = Vector4f(y, y, y, y)
}