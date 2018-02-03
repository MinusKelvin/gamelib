package minuskelvin.gamelib.math

import kotlin.math.roundToInt
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
    
    operator fun Int.times(rhs: Vector2i) = Vector2i(
            x = this * rhs.x,
            y = this * rhs.y
    )

    operator fun div(rhs: Int) = Vector2i(
            x = this.x / rhs,
            y = this.y / rhs
    )

    fun lerp(to: Vector2i, alpha: Double) = Vector2i(
            x = ((1 - alpha) * x + alpha * to.x).roundToInt(),
            y = ((1 - alpha) * y + alpha * to.y).roundToInt()
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
    
    val xx get() = Vector2i(x, x)
    val yx get() = Vector2i(y, x)
    val xy get() = this
    val yy get() = Vector2i(y, y)

    val xxx get() = Vector3i(x, x, x)
    val yxx get() = Vector3i(y, x, x)
    val xyx get() = Vector3i(x, y, x)
    val yyx get() = Vector3i(y, y, x)
    val xxy get() = Vector3i(x, x, y)
    val yxy get() = Vector3i(y, x, y)
    val xyy get() = Vector3i(x, y, y)
    val yyy get() = Vector3i(y, y, y)

    val xxxx get() = Vector4i(x, x, x, x)
    val yxxx get() = Vector4i(y, x, x, x)
    val xyxx get() = Vector4i(x, y, x, x)
    val yyxx get() = Vector4i(y, y, x, x)
    val xxyx get() = Vector4i(x, x, y, x)
    val yxyx get() = Vector4i(y, x, y, x)
    val xyyx get() = Vector4i(x, y, y, x)
    val yyyx get() = Vector4i(y, y, y, x)
    val xxxy get() = Vector4i(x, x, x, y)
    val yxxy get() = Vector4i(y, x, x, y)
    val xyxy get() = Vector4i(x, y, x, y)
    val yyxy get() = Vector4i(y, y, x, y)
    val xxyy get() = Vector4i(x, x, y, y)
    val yxyy get() = Vector4i(y, x, y, y)
    val xyyy get() = Vector4i(x, y, y, y)
    val yyyy get() = Vector4i(y, y, y, y)
}