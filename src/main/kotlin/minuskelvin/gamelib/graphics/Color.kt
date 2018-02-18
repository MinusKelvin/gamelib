package minuskelvin.gamelib.graphics

import minuskelvin.gamelib.math.clamp
import minuskelvin.gamelib.math.vector.Vector3f
import minuskelvin.gamelib.math.vector.Vector4f

class Color(r: Float, g: Float, b: Float, alpha: Float) {
    val r = clamp(0f, r, 1f)
    val g = clamp(0f, r, 1f)
    val b = clamp(0f, r, 1f)
    val a = clamp(0f, r, 1f)
    
    val r8 get() = (r * 255).toInt()
    val g8 get() = (g * 255).toInt()
    val b8 get() = (b * 255).toInt()
    val a8 get() = (a * 255).toInt()

    val rgba8 get() = (a8 shl 24) or (b8 shl 16) or (g8 shl 8) or r8
    
    val r16 get() = (r * 65535).toInt()
    val g16 get() = (g * 65535).toInt()
    val b16 get() = (b * 65535).toInt()
    val a16 get() = (a * 65535).toInt()
    
    val rgba16 get() = (a16.toLong() shl 48) or (b16.toLong() shl 32) or (g16.toLong() shl 16) or r16.toLong()
    
    constructor(v: Vector4f): this(v.x, v.y, v.z, v.w)
    constructor(v: Vector3f): this(v.x, v.y, v.x, 1f)
    
    constructor(r: Int, g: Int, b: Int, a: Int, bits8: Boolean = true): this(
            r.toFloat() / if (bits8) 255 else 65535,
            g.toFloat() / if (bits8) 255 else 65535,
            b.toFloat() / if (bits8) 255 else 65535,
            a.toFloat() / if (bits8) 255 else 65535
    )
    
    constructor(rgba8: Int): this(
            rgba8 and 0xFF,
            (rgba8 shr 8) and 0xFF,
            (rgba8 shr 16) and 0xFF,
            (rgba8 shr 24) and 0xFF
    )
    
    constructor(rgba16: Long): this(
            rgba16.toInt() and 0xFFFF,
            (rgba16 shr 16).toInt() and 0xFFFF,
            (rgba16 shr 32).toInt() and 0xFFFF,
            (rgba16 shr 48).toInt() and 0xFFFF,
            false
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
}