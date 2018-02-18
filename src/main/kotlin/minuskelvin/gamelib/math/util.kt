package minuskelvin.gamelib.math

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