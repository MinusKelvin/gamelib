package minuskelvin.gamelib.graphics

import minuskelvin.gamelib.math.clamp
import minuskelvin.gamelib.math.vector.Vector3f
import minuskelvin.gamelib.math.vector.Vector4f

class Color(r: Float, g: Float, b: Float, a: Float = 1f) {
    val r = clamp(0f, r, 1f)
    val g = clamp(0f, g, 1f)
    val b = clamp(0f, b, 1f)
    val a = clamp(0f, a, 1f)
    
    val r8 get() = (r * 255).toInt()
    val g8 get() = (g * 255).toInt()
    val b8 get() = (b * 255).toInt()
    val a8 get() = (a * 255).toInt()

    val rgba get() = (a8 shl 24) or (b8 shl 16) or (g8 shl 8) or r8
    
    constructor(v: Vector4f): this(v.x, v.y, v.z, v.w)
    constructor(v: Vector3f): this(v.x, v.y, v.x, 1f)
    
    constructor(r: Int, g: Int, b: Int, a: Int = 255): this(
            r.toFloat() / 255,
            g.toFloat() / 255,
            b.toFloat() / 255,
            a.toFloat() / 255
    )
    
    constructor(rgba8: Int): this(
            rgba8 and 0xFF,
            (rgba8 shr 8) and 0xFF,
            (rgba8 shr 16) and 0xFF,
            (rgba8 shr 24) and 0xFF
    )

    operator fun component1() = r
    operator fun component2() = g
    operator fun component3() = b
    operator fun component4() = a
    
    override fun equals(other: Any?) = when (other) {
        is Color -> r == other.r && g == other.g && b == other.b && a == other.a
        else -> false
    }

    override fun hashCode(): Int {
        var hash = r.hashCode()
        hash = hash * 31 + g.hashCode()
        hash = hash * 31 + b.hashCode()
        hash = hash * 31 + a.hashCode()
        return hash
    }

    override fun toString() = "Color(r=$r, g=$g, b=$b, a=$a)"
    
    fun copy(r: Float = this.r, g: Float = this.g, b: Float = this.b, a: Float = this.a) = Color(r,g,b,a)
    
    companion object {
        val WHITE = Color(1f, 1f, 1f)
        val RED   = Color(1f, 0f, 0f)
        val GREEN = Color(0f, 1f, 0f)
        val BLUE  = Color(0f, 0f, 1f)
        val BLACK = Color(0f, 0f, 0f)

        val TRANSPARENT_BLACK = Color(0f, 0f, 0f, 0f)
        val TRANSPARENT_WHITE = Color(1f, 1f, 1f, 0f)
    }
}