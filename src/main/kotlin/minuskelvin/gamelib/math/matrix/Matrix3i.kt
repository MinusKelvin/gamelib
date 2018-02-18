package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.lerp
import minuskelvin.gamelib.math.vector.Vector3i

/**
 * ```
 * [ m00 m10 m20 ]
 * [ m01 m11 m21 ]
 * [ m02 m12 m22 ]
 * ```
 */
data class Matrix3i(
        val m00: Int, val m01: Int, val m02: Int, // Column 0
        val m10: Int, val m11: Int, val m12: Int, // Column 1
        val m20: Int, val m21: Int, val m22: Int  // Column 2
) {
    constructor(): this(
            m00 = 1, m10 = 0, m20 = 0,
            m01 = 0, m11 = 1, m21 = 0,
            m02 = 0, m12 = 0, m22 = 1
    )

    constructor(column0: Vector3i, column1: Vector3i, column2: Vector3i): this(
            m00 = column0.x, m10 = column1.x, m20 = column2.x,
            m01 = column0.y, m11 = column1.y, m21 = column2.y,
            m02 = column0.z, m12 = column1.z, m22 = column2.z
    )

    operator fun plus(rhs: Matrix3i) = Matrix3i(
            m00 = this.m00 + rhs.m00, m10 = this.m10 + rhs.m10, m20 = this.m20 + rhs.m20,
            m01 = this.m01 + rhs.m01, m11 = this.m11 + rhs.m11, m21 = this.m21 + rhs.m21,
            m02 = this.m02 + rhs.m02, m12 = this.m12 + rhs.m12, m22 = this.m22 + rhs.m22
    )

    operator fun minus(rhs: Matrix3i) = Matrix3i(
            m00 = this.m00 - rhs.m00, m10 = this.m10 - rhs.m10, m20 = this.m20 - rhs.m20,
            m01 = this.m01 - rhs.m01, m11 = this.m11 - rhs.m11, m21 = this.m21 - rhs.m21,
            m02 = this.m02 - rhs.m02, m12 = this.m12 - rhs.m12, m22 = this.m22 - rhs.m22
    )

    operator fun times(rhs: Matrix3i) = Matrix3i(
            column0 = this * rhs.column0,
            column1 = this * rhs.column1,
            column2 = this * rhs.column2
    )
    
    operator fun times(rhs: Vector3i) = Vector3i(
            x = this.row0 dot rhs,
            y = this.row1 dot rhs,
            z = this.row2 dot rhs
    )

    operator fun times(rhs: Int) = Matrix3i(
            m00 = m00 * rhs, m10 = m10 * rhs, m20 = m20 * rhs,
            m01 = m01 * rhs, m11 = m11 * rhs, m21 = m21 * rhs,
            m02 = m02 * rhs, m12 = m12 * rhs, m22 = m22 * rhs
    )

    operator fun div(rhs: Int) = Matrix3i(
            m00 = m00 / rhs, m10 = m10 / rhs, m20 = m20 / rhs,
            m01 = m01 / rhs, m11 = m11 / rhs, m21 = m21 / rhs,
            m02 = m02 / rhs, m12 = m12 / rhs, m22 = m22 / rhs
    )

    infix fun mulComponents(rhs: Matrix3i) = Matrix3i(
            m00 = this.m00 * rhs.m00, m10 = this.m10 * rhs.m10, m20 = this.m20 * rhs.m20,
            m01 = this.m01 * rhs.m01, m11 = this.m11 * rhs.m11, m21 = this.m21 * rhs.m21,
            m02 = this.m02 * rhs.m02, m12 = this.m12 * rhs.m12, m22 = this.m22 * rhs.m22
    )

    infix fun divComponents(rhs: Matrix3i) = Matrix3i(
            m00 = this.m00 / rhs.m00, m10 = this.m10 / rhs.m10, m20 = this.m20 / rhs.m20,
            m01 = this.m01 / rhs.m01, m11 = this.m11 / rhs.m11, m21 = this.m21 / rhs.m21,
            m02 = this.m02 / rhs.m02, m12 = this.m12 / rhs.m12, m22 = this.m22 / rhs.m22
    )

    fun inverse() = Matrix3i( // Matrix of cofactors, transposed
            m00 =   m11 * m22 - m12 * m21,  m01 = -(m01 * m22 - m02 * m21), m02 =   m01 * m12 - m02 * m11,
            m10 = -(m10 * m22 - m12 * m20), m11 =   m00 * m22 - m02 * m20,  m12 = -(m00 * m12 - m02 * m10),
            m20 =   m10 * m21 - m11 * m20,  m21 = -(m00 * m21 - m01 * m20), m22 =   m00 * m11 - m01 * m10
    ) / determinant

    fun transpose() = Matrix3i(
            m00 = m00, m10 = m01, m20 = m02,
            m01 = m10, m11 = m11, m21 = m12,
            m02 = m20, m12 = m21, m22 = m22
    )

    val determinant get() =
            m00 * (m11 * m22 - m12 * m21) -
            m10 * (m01 * m22 - m02 * m21) +
            m20 * (m01 * m12 - m02 * m11)

    fun lerp(to: Matrix3i, alpha: Double) = Matrix3i(
            m00 = m00.lerp(to.m00, alpha), m10 = m10.lerp(to.m10, alpha), m20 = m20.lerp(to.m20, alpha),
            m01 = m01.lerp(to.m01, alpha), m11 = m11.lerp(to.m11, alpha), m21 = m21.lerp(to.m21, alpha),
            m02 = m02.lerp(to.m02, alpha), m12 = m12.lerp(to.m12, alpha), m22 = m22.lerp(to.m22, alpha)
    )

    fun toFloat() = Matrix3f(
            m00 = m00.toFloat(), m10 = m10.toFloat(), m20 = m20.toFloat(),
            m01 = m01.toFloat(), m11 = m11.toFloat(), m21 = m21.toFloat(),
            m02 = m02.toFloat(), m12 = m12.toFloat(), m22 = m22.toFloat()
    )

    fun toDouble() = Matrix3d(
            m00 = m00.toDouble(), m10 = m10.toDouble(), m20 = m20.toDouble(),
            m01 = m01.toDouble(), m11 = m11.toDouble(), m21 = m21.toDouble(),
            m02 = m02.toDouble(), m12 = m12.toDouble(), m22 = m22.toDouble()
    )

    val row0 get() = Vector3i(m00, m10, m20)
    val row1 get() = Vector3i(m01, m11, m21)
    val row2 get() = Vector3i(m02, m12, m22)

    val column0 get() = Vector3i(m00, m01, m02)
    val column1 get() = Vector3i(m10, m11, m12)
    val column2 get() = Vector3i(m20, m21, m22)
}

operator fun Int.times(rhs: Matrix3i) = Matrix3i(
        m00 = this * rhs.m00, m10 = this * rhs.m10, m20 = this * rhs.m20,
        m01 = this * rhs.m01, m11 = this * rhs.m11, m21 = this * rhs.m21,
        m02 = this * rhs.m02, m12 = this * rhs.m12, m22 = this * rhs.m22
)