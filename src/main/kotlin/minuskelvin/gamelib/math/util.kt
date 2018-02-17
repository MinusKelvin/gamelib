package minuskelvin.gamelib.math

import kotlin.math.roundToInt

fun Double.lerp(to: Double, alpha: Double) = this * (1 - alpha) + to * alpha
fun Float .lerp(to: Float,  alpha: Float)  = this * (1 - alpha) + to * alpha
fun Int   .lerp(to: Int,    alpha: Double) =(this * (1 - alpha) + to * alpha).roundToInt()

fun Short.toIntUnsigned() = toInt() and 0xFFFF
fun Byte .toIntUnsigned() = toInt() and 0xFF