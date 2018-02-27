package minuskelvin.gamelib.math

import minuskelvin.gamelib.math.vector.Vector2d
import minuskelvin.gamelib.math.vector.Vector2f
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

fun Double.lerp(to: Double, alpha: Double) = this * (1 - alpha) + to * alpha
fun Float .lerp(to: Float,  alpha: Float)  = this * (1 - alpha) + to * alpha
fun Int   .lerp(to: Int,    alpha: Double) =(this * (1 - alpha) + to * alpha).roundToInt()

fun Short.toIntUnsigned() = toInt() and 0xFFFF
fun Byte .toIntUnsigned() = toInt() and 0xFF

fun clamp(lower: Float,  value: Float,  higher: Float)  = max(lower, min(value, higher))
fun clamp(lower: Double, value: Double, higher: Double) = max(lower, min(value, higher))
fun clamp(lower: Int,    value: Int,    higher: Int)    = max(lower, min(value, higher))

fun rotate(pos: Vector2f, dir: Vector2f) = Vector2f(
        x = pos.x * dir.x - pos.y * dir.y,
        y = pos.y * dir.x + pos.x * dir.y
)

fun rotate(pos: Vector2d, dir: Vector2d) = Vector2d(
        x = pos.x * dir.x - pos.y * dir.y,
        y = pos.y * dir.x + pos.x * dir.y
)