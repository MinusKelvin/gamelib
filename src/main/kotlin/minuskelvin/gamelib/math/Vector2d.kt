package minuskelvin.gamelib.math

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
    
    operator fun Float.times(rhs: Vector2d) = Vector2d(
            x = this * rhs.x,
            y = this * rhs.y
    )

    operator fun div(rhs: Double) = Vector2d(
            x = this.x / rhs,
            y = this.y / rhs
    )
    
    infix fun dot(rhs: Vector2d) = this.x * rhs.x + this.y * rhs.y
    
    fun lerp(to: Vector2d, alpha: Double) = Vector2d(
            x = (1 - alpha) * x + alpha * to.x,
            y = (1 - alpha) * y + alpha * to.y
    )

    fun toFloat() = Vector2f(
            x = x.toFloat(),
            y = y.toFloat()
    )
    
    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)
    
    val unit get() = this / length
    
    val xx get() = Vector2d(x, x)
    val yx get() = Vector2d(y, x)
    val xy get() = this
    val yy get() = Vector2d(y, y)

    val xxx get() = Vector3d(x, x, x)
    val yxx get() = Vector3d(y, x, x)
    val xyx get() = Vector3d(x, y, x)
    val yyx get() = Vector3d(y, y, x)
    val xxy get() = Vector3d(x, x, y)
    val yxy get() = Vector3d(y, x, y)
    val xyy get() = Vector3d(x, y, y)
    val yyy get() = Vector3d(y, y, y)

    val xxxx get() = Vector4d(x, x, x, x)
    val yxxx get() = Vector4d(y, x, x, x)
    val xyxx get() = Vector4d(x, y, x, x)
    val yyxx get() = Vector4d(y, y, x, x)
    val xxyx get() = Vector4d(x, x, y, x)
    val yxyx get() = Vector4d(y, x, y, x)
    val xyyx get() = Vector4d(x, y, y, x)
    val yyyx get() = Vector4d(y, y, y, x)
    val xxxy get() = Vector4d(x, x, x, y)
    val yxxy get() = Vector4d(y, x, x, y)
    val xyxy get() = Vector4d(x, y, x, y)
    val yyxy get() = Vector4d(y, y, x, y)
    val xxyy get() = Vector4d(x, x, y, y)
    val yxyy get() = Vector4d(y, x, y, y)
    val xyyy get() = Vector4d(x, y, y, y)
    val yyyy get() = Vector4d(y, y, y, y)
}