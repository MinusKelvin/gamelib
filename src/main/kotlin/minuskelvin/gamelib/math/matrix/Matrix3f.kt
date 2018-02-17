package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.lerp
import minuskelvin.gamelib.math.vector.Vector3f

/**
 * ```
 * [ m00 m10 m20 ]
 * [ m01 m11 m21 ]
 * [ m02 m12 m22 ]
 * ```
 */
data class Matrix3f(
        val m00: Float, val m01: Float, val m02: Float, // Column 0
        val m10: Float, val m11: Float, val m12: Float, // Column 1
        val m20: Float, val m21: Float, val m22: Float  // Column 2
) {
    constructor(): this(
            m00 = 1f, m10 = 0f, m20 = 0f,
            m01 = 0f, m11 = 1f, m21 = 0f,
            m02 = 0f, m12 = 0f, m22 = 1f
    )

    constructor(column0: Vector3f, column1: Vector3f, column2: Vector3f): this(
            m00 = column0.x, m10 = column1.x, m20 = column2.x,
            m01 = column0.y, m11 = column1.y, m21 = column2.y,
            m02 = column0.z, m12 = column1.z, m22 = column2.z
    )

    operator fun plus(rhs: Matrix3f) = Matrix3f(
            m00 = this.m00 + rhs.m00, m10 = this.m10 + rhs.m10, m20 = this.m20 + rhs.m20,
            m01 = this.m01 + rhs.m01, m11 = this.m11 + rhs.m11, m21 = this.m21 + rhs.m21,
            m02 = this.m02 + rhs.m02, m12 = this.m12 + rhs.m12, m22 = this.m22 + rhs.m22
    )

    operator fun minus(rhs: Matrix3f) = Matrix3f(
            m00 = this.m00 - rhs.m00, m10 = this.m10 - rhs.m10, m20 = this.m20 - rhs.m20,
            m01 = this.m01 - rhs.m01, m11 = this.m11 - rhs.m11, m21 = this.m21 - rhs.m21,
            m02 = this.m02 - rhs.m02, m12 = this.m12 - rhs.m12, m22 = this.m22 - rhs.m22
    )

    operator fun times(rhs: Matrix3f) = Matrix3f(
            column0 = this * rhs.column0,
            column1 = this * rhs.column1,
            column2 = this * rhs.column2
    )
    
    operator fun times(rhs: Vector3f) = Vector3f(
            x = this.row0 dot rhs,
            y = this.row1 dot rhs,
            z = this.row2 dot rhs
    )

    operator fun times(rhs: Float) = Matrix3f(
            m00 = m00 * rhs, m10 = m10 * rhs, m20 = m20 * rhs,
            m01 = m01 * rhs, m11 = m11 * rhs, m21 = m21 * rhs,
            m02 = m02 * rhs, m12 = m12 * rhs, m22 = m22 * rhs
    )

    operator fun div(rhs: Float) = this * (1f / rhs)

    infix fun mulComponents(rhs: Matrix3f) = Matrix3f(
            m00 = this.m00 * rhs.m00, m10 = this.m10 * rhs.m10, m20 = this.m20 * rhs.m20,
            m01 = this.m01 * rhs.m01, m11 = this.m11 * rhs.m11, m21 = this.m21 * rhs.m21,
            m02 = this.m02 * rhs.m02, m12 = this.m12 * rhs.m12, m22 = this.m22 * rhs.m22
    )

    infix fun divComponents(rhs: Matrix3f) = Matrix3f(
            m00 = this.m00 / rhs.m00, m10 = this.m10 / rhs.m10, m20 = this.m20 / rhs.m20,
            m01 = this.m01 / rhs.m01, m11 = this.m11 / rhs.m11, m21 = this.m21 / rhs.m21,
            m02 = this.m02 / rhs.m02, m12 = this.m12 / rhs.m12, m22 = this.m22 / rhs.m22
    )

    fun inverse() = Matrix3f( // Matrix of cofactors, transposed
            m00 =   m11 * m22 - m12 * m21,  m01 = -(m01 * m22 - m02 * m21), m02 =   m01 * m12 - m02 * m11,
            m10 = -(m10 * m22 - m12 * m20), m11 =   m00 * m22 - m02 * m20,  m12 = -(m00 * m12 - m02 * m10),
            m20 =   m10 * m21 - m11 * m20,  m21 = -(m00 * m21 - m01 * m20), m22 =   m00 * m11 - m01 * m10
    ) / determinant

    fun transpose() = Matrix3f(
            m00 = m00, m10 = m01, m20 = m02,
            m01 = m10, m11 = m11, m21 = m12,
            m02 = m20, m12 = m21, m22 = m22
    )

    val determinant get() =
            m00 * (m11 * m22 - m12 * m21) -
            m10 * (m01 * m22 - m02 * m21) +
            m20 * (m01 * m12 - m02 * m11)

    fun lerp(to: Matrix3f, alpha: Float) = Matrix3f(
            m00 = m00.lerp(to.m00, alpha), m10 = m10.lerp(to.m10, alpha), m20 = m20.lerp(to.m20, alpha),
            m01 = m01.lerp(to.m01, alpha), m11 = m11.lerp(to.m11, alpha), m21 = m21.lerp(to.m21, alpha),
            m02 = m02.lerp(to.m02, alpha), m12 = m12.lerp(to.m12, alpha), m22 = m22.lerp(to.m22, alpha)
    )

    fun toDouble() = Matrix3d(
            m00 = m00.toDouble(), m10 = m10.toDouble(), m20 = m20.toDouble(),
            m01 = m01.toDouble(), m11 = m11.toDouble(), m21 = m21.toDouble(),
            m02 = m02.toDouble(), m12 = m12.toDouble(), m22 = m22.toDouble()
    )

    fun toInt() = Matrix3i(
            m00 = m00.toInt(), m10 = m10.toInt(), m20 = m20.toInt(),
            m01 = m01.toInt(), m11 = m11.toInt(), m21 = m21.toInt(),
            m02 = m02.toInt(), m12 = m12.toInt(), m22 = m22.toInt()
    )

    val row0 get() = Vector3f(m00, m10, m20)
    val row1 get() = Vector3f(m01, m11, m21)
    val row2 get() = Vector3f(m02, m12, m22)

    val column0 get() = Vector3f(m00, m01, m02)
    val column1 get() = Vector3f(m10, m11, m12)
    val column2 get() = Vector3f(m20, m21, m22)
}

operator fun Float.times(rhs: Matrix3f) = Matrix3f(
        m00 = this * rhs.m00, m10 = this * rhs.m10, m20 = this * rhs.m20,
        m01 = this * rhs.m01, m11 = this * rhs.m11, m21 = this * rhs.m21,
        m02 = this * rhs.m02, m12 = this * rhs.m12, m22 = this * rhs.m22
)