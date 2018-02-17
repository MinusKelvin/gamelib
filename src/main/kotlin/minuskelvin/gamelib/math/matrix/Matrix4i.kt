package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.lerp
import minuskelvin.gamelib.math.vector.Vector4i

/**
 * ```
 * [ m00 m10 m20 m30 ]
 * [ m01 m11 m21 m31 ]
 * [ m02 m12 m22 m32 ]
 * [ m03 m13 m23 m33 ]
 * ```
 */
data class Matrix4i(
        val m00: Int, val m01: Int, val m02: Int, val m03: Int, // Column 0
        val m10: Int, val m11: Int, val m12: Int, val m13: Int, // Column 1
        val m20: Int, val m21: Int, val m22: Int, val m23: Int, // Column 2
        val m30: Int, val m31: Int, val m32: Int, val m33: Int  // Column 3
) {
    constructor(): this(
            m00 = 1, m10 = 0, m20 = 0, m30 = 0,
            m01 = 0, m11 = 1, m21 = 0, m31 = 0,
            m02 = 0, m12 = 0, m22 = 1, m32 = 0,
            m03 = 0, m13 = 0, m23 = 0, m33 = 1
    )

    constructor(column0: Vector4i, column1: Vector4i, column2: Vector4i, column3: Vector4i): this(
            m00 = column0.x, m10 = column1.x, m20 = column2.x, m30 = column3.x,
            m01 = column0.y, m11 = column1.y, m21 = column2.y, m31 = column3.y,
            m02 = column0.z, m12 = column1.z, m22 = column2.z, m32 = column3.z,
            m03 = column0.w, m13 = column1.w, m23 = column2.w, m33 = column3.w
    )

    operator fun plus(rhs: Matrix4i) = Matrix4i(
            m00 = this.m00 + rhs.m00, m10 = this.m10 + rhs.m10, m20 = this.m20 + rhs.m20, m30 = this.m30 + rhs.m30,
            m01 = this.m01 + rhs.m01, m11 = this.m11 + rhs.m11, m21 = this.m21 + rhs.m21, m31 = this.m31 + rhs.m31,
            m02 = this.m02 + rhs.m02, m12 = this.m12 + rhs.m12, m22 = this.m22 + rhs.m22, m32 = this.m32 + rhs.m32,
            m03 = this.m03 + rhs.m03, m13 = this.m13 + rhs.m13, m23 = this.m23 + rhs.m23, m33 = this.m33 + rhs.m33
    )

    operator fun minus(rhs: Matrix4i) = Matrix4i(
            m00 = this.m00 - rhs.m00, m10 = this.m10 - rhs.m10, m20 = this.m20 - rhs.m20, m30 = this.m30 - rhs.m30,
            m01 = this.m01 - rhs.m01, m11 = this.m11 - rhs.m11, m21 = this.m21 - rhs.m21, m31 = this.m31 - rhs.m31,
            m02 = this.m02 - rhs.m02, m12 = this.m12 - rhs.m12, m22 = this.m22 - rhs.m22, m32 = this.m32 - rhs.m32,
            m03 = this.m03 - rhs.m03, m13 = this.m13 - rhs.m13, m23 = this.m23 - rhs.m23, m33 = this.m33 - rhs.m33
    )
    
    operator fun times(rhs: Matrix4i) = Matrix4i(
            column0 = this * rhs.column0,
            column1 = this * rhs.column1,
            column2 = this * rhs.column2,
            column3 = this * rhs.column3
    )
    
    operator fun times(rhs: Vector4i) = Vector4i(
            x = this.row0 dot rhs,
            y = this.row1 dot rhs,
            z = this.row2 dot rhs,
            w = this.row3 dot rhs
    )
    
    operator fun times(rhs: Int) = Matrix4i(
            m00 = this.m00 * rhs, m10 = this.m10 * rhs, m20 = this.m20 * rhs, m30 = this.m30 * rhs,
            m01 = this.m01 * rhs, m11 = this.m11 * rhs, m21 = this.m21 * rhs, m31 = this.m31 * rhs,
            m02 = this.m02 * rhs, m12 = this.m12 * rhs, m22 = this.m22 * rhs, m32 = this.m32 * rhs,
            m03 = this.m03 * rhs, m13 = this.m13 * rhs, m23 = this.m23 * rhs, m33 = this.m33 * rhs
    )
    
    operator fun div(rhs: Int) = Matrix4i(
            m00 = this.m00 / rhs, m10 = this.m10 / rhs, m20 = this.m20 / rhs, m30 = this.m30 / rhs,
            m01 = this.m01 / rhs, m11 = this.m11 / rhs, m21 = this.m21 / rhs, m31 = this.m31 / rhs,
            m02 = this.m02 / rhs, m12 = this.m12 / rhs, m22 = this.m22 / rhs, m32 = this.m32 / rhs,
            m03 = this.m03 / rhs, m13 = this.m13 / rhs, m23 = this.m23 / rhs, m33 = this.m33 / rhs
    )

    infix fun mulComponents(rhs: Matrix4i) = Matrix4i(
            m00 = this.m00 * rhs.m00, m10 = this.m10 * rhs.m10, m20 = this.m20 * rhs.m20, m30 = this.m30 * rhs.m30,
            m01 = this.m01 * rhs.m01, m11 = this.m11 * rhs.m11, m21 = this.m21 * rhs.m21, m31 = this.m31 * rhs.m31,
            m02 = this.m02 * rhs.m02, m12 = this.m12 * rhs.m12, m22 = this.m22 * rhs.m22, m32 = this.m32 * rhs.m32,
            m03 = this.m03 * rhs.m03, m13 = this.m13 * rhs.m13, m23 = this.m23 * rhs.m23, m33 = this.m33 * rhs.m33
    )

    infix fun divComponents(rhs: Matrix4i) = Matrix4i(
            m00 = this.m00 / rhs.m00, m10 = this.m10 / rhs.m10, m20 = this.m20 / rhs.m20, m30 = this.m30 / rhs.m30,
            m01 = this.m01 / rhs.m01, m11 = this.m11 / rhs.m11, m21 = this.m21 / rhs.m21, m31 = this.m31 / rhs.m31,
            m02 = this.m02 / rhs.m02, m12 = this.m12 / rhs.m12, m22 = this.m22 / rhs.m22, m32 = this.m32 / rhs.m32,
            m03 = this.m03 / rhs.m03, m13 = this.m13 / rhs.m13, m23 = this.m23 / rhs.m23, m33 = this.m33 / rhs.m33
    )
    
    fun inverse() = Matrix4d( // Matrix of cofactors, transposed
            m00 =  minorDet00.toDouble(), m01 = -minorDet10.toDouble(), m02 =  minorDet20.toDouble(), m03 = -minorDet30.toDouble(),
            m10 = -minorDet01.toDouble(), m11 =  minorDet11.toDouble(), m12 = -minorDet21.toDouble(), m13 =  minorDet31.toDouble(),
            m20 =  minorDet02.toDouble(), m21 = -minorDet12.toDouble(), m22 =  minorDet22.toDouble(), m23 = -minorDet32.toDouble(),
            m30 = -minorDet03.toDouble(), m31 =  minorDet13.toDouble(), m32 = -minorDet23.toDouble(), m33 =  minorDet33.toDouble()
    ) / determinant.toDouble()
    
    fun transpose() = Matrix4i(
            m00 = m00, m10 = m01, m20 = m02, m30 = m03,
            m01 = m10, m11 = m11, m21 = m12, m31 = m13,
            m02 = m20, m12 = m21, m22 = m22, m32 = m23,
            m03 = m30, m13 = m31, m23 = m32, m33 = m33
    )
    
    val determinant get() = m00 * minorDet00 - m10 * minorDet10 + m20 * minorDet20 - m30 * minorDet30
    
    fun lerp(to: Matrix4i, alpha: Double) = Matrix4i(
            m00 = m00.lerp(to.m00, alpha), m10 = m10.lerp(to.m10, alpha), m20 = m20.lerp(to.m20, alpha), m30 = m30.lerp(to.m30, alpha),
            m01 = m01.lerp(to.m01, alpha), m11 = m11.lerp(to.m11, alpha), m21 = m21.lerp(to.m21, alpha), m31 = m31.lerp(to.m31, alpha),
            m02 = m02.lerp(to.m02, alpha), m12 = m12.lerp(to.m12, alpha), m22 = m22.lerp(to.m22, alpha), m32 = m32.lerp(to.m32, alpha),
            m03 = m03.lerp(to.m03, alpha), m13 = m13.lerp(to.m13, alpha), m23 = m23.lerp(to.m23, alpha), m33 = m33.lerp(to.m33, alpha)
    )
    
    fun toFloat() = Matrix4f(
            m00 = m00.toFloat(), m10 = m10.toFloat(), m20 = m20.toFloat(), m30 = m30.toFloat(),
            m01 = m01.toFloat(), m11 = m11.toFloat(), m21 = m21.toFloat(), m31 = m31.toFloat(),
            m02 = m02.toFloat(), m12 = m12.toFloat(), m22 = m22.toFloat(), m32 = m32.toFloat(),
            m03 = m03.toFloat(), m13 = m13.toFloat(), m23 = m23.toFloat(), m33 = m33.toFloat()
    )

    fun toDouble() = Matrix4d(
            m00 = m00.toDouble(), m10 = m10.toDouble(), m20 = m20.toDouble(), m30 = m30.toDouble(),
            m01 = m01.toDouble(), m11 = m11.toDouble(), m21 = m21.toDouble(), m31 = m31.toDouble(),
            m02 = m02.toDouble(), m12 = m12.toDouble(), m22 = m22.toDouble(), m32 = m32.toDouble(),
            m03 = m03.toDouble(), m13 = m13.toDouble(), m23 = m23.toDouble(), m33 = m33.toDouble()
    )

    val row0 get() = Vector4i(m00, m10, m20, m30)
    val row1 get() = Vector4i(m01, m11, m21, m31)
    val row2 get() = Vector4i(m02, m12, m22, m32)
    val row3 get() = Vector4i(m03, m13, m23, m33)

    val column0 get() = Vector4i(m00, m01, m02, m03)
    val column1 get() = Vector4i(m10, m11, m12, m13)
    val column2 get() = Vector4i(m20, m21, m22, m23)
    val column3 get() = Vector4i(m30, m31, m32, m33)
}

// These would be inlined in-code if it weren't for their sheer size.
// So, we tell the compiler to do that for us.

private inline val Matrix4i.minorDet30 get() = m01 * (m12 * m23 - m13 * m22) - m11 * (m02 * m23 - m03 * m22) + m21 * (m02 * m13 - m03 * m12)
private inline val Matrix4i.minorDet31 get() = m00 * (m12 * m23 - m13 * m22) - m10 * (m02 * m23 - m03 * m22) + m20 * (m02 * m13 - m03 * m12)
private inline val Matrix4i.minorDet32 get() = m00 * (m11 * m23 - m13 * m21) - m10 * (m01 * m23 - m03 * m21) + m20 * (m01 * m13 - m03 * m11)
private inline val Matrix4i.minorDet33 get() = m00 * (m11 * m22 - m12 * m21) - m10 * (m01 * m22 - m02 * m21) + m20 * (m01 * m12 - m02 * m11)

private inline val Matrix4i.minorDet20 get() = m01 * (m12 * m33 - m13 * m32) - m11 * (m02 * m33 - m03 * m32) + m31 * (m02 * m13 - m03 * m12)
private inline val Matrix4i.minorDet21 get() = m00 * (m12 * m33 - m13 * m32) - m10 * (m02 * m33 - m03 * m32) + m30 * (m02 * m13 - m03 * m12)
private inline val Matrix4i.minorDet22 get() = m00 * (m11 * m33 - m13 * m31) - m10 * (m01 * m33 - m03 * m31) + m30 * (m01 * m13 - m03 * m11)
private inline val Matrix4i.minorDet23 get() = m00 * (m11 * m32 - m12 * m31) - m10 * (m01 * m32 - m02 * m31) + m30 * (m01 * m12 - m02 * m11)

private inline val Matrix4i.minorDet10 get() = m01 * (m22 * m33 - m23 * m32) - m21 * (m02 * m33 - m03 * m32) + m21 * (m02 * m23 - m03 * m22)
private inline val Matrix4i.minorDet11 get() = m00 * (m22 * m33 - m23 * m32) - m20 * (m02 * m33 - m03 * m32) + m20 * (m02 * m23 - m03 * m22)
private inline val Matrix4i.minorDet12 get() = m00 * (m21 * m33 - m23 * m31) - m20 * (m01 * m33 - m03 * m31) + m20 * (m01 * m23 - m03 * m21)
private inline val Matrix4i.minorDet13 get() = m00 * (m21 * m32 - m22 * m31) - m20 * (m01 * m32 - m02 * m31) + m20 * (m01 * m22 - m02 * m21)

private inline val Matrix4i.minorDet00 get() = m11 * (m22 * m33 - m23 * m32) - m21 * (m12 * m33 - m13 * m32) + m21 * (m12 * m23 - m13 * m22)
private inline val Matrix4i.minorDet01 get() = m10 * (m22 * m33 - m23 * m32) - m20 * (m12 * m33 - m13 * m32) + m20 * (m12 * m23 - m13 * m22)
private inline val Matrix4i.minorDet02 get() = m10 * (m21 * m33 - m23 * m31) - m20 * (m11 * m33 - m13 * m31) + m20 * (m11 * m23 - m13 * m21)
private inline val Matrix4i.minorDet03 get() = m10 * (m21 * m32 - m22 * m31) - m20 * (m11 * m32 - m12 * m31) + m20 * (m11 * m22 - m12 * m21)