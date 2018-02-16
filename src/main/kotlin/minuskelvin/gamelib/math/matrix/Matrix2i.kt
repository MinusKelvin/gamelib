package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.lerp
import minuskelvin.gamelib.math.vector.Vector2i

/**
 * ```
 * [ m00 m10 ]
 * [ m01 m11 ]
 * ```
 */
data class Matrix2i(
        val m00: Int, val m01: Int, // Column 0
        val m10: Int, val m11: Int  // Column 1
) {
    constructor(): this(
            m00 = 1, m10 = 0,
            m01 = 0, m11 = 1
    )
    
    constructor(column0: Vector2i, column1: Vector2i): this(
            m00 = column0.x, m10 = column1.x,
            m01 = column0.y, m11 = column1.y
    )
    
    operator fun plus(rhs: Matrix2i) = Matrix2i(
            m00 = this.m00 + rhs.m00, m10 = this.m10 + rhs.m10,
            m01 = this.m01 + rhs.m01, m11 = this.m11 + rhs.m11
    )
    
    operator fun minus(rhs: Matrix2i) = Matrix2i(
            m00 = this.m00 - rhs.m00, m10 = this.m10 - rhs.m10,
            m01 = this.m01 - rhs.m01, m11 = this.m11 - rhs.m11
    )
    
    operator fun times(rhs: Matrix2i) = Matrix2i(
            column0 = this * rhs.column0,
            column1 = this * rhs.column1
    )
    
    operator fun times(rhs: Vector2i) = Vector2i(
            x = this.row0 dot rhs,
            y = this.row1 dot rhs
    )
    
    operator fun times(rhs: Int) = Matrix2i(
            m00 = m00 * rhs, m10 = m10 * rhs,
            m01 = m01 * rhs, m11 = m11 * rhs
    )
    
    operator fun div(rhs: Int) = Matrix2i(
            m00 = m00 / rhs, m10 = m10 / rhs,
            m01 = m01 / rhs, m11 = m11 / rhs
    )
    
    infix fun mulComponents(rhs: Matrix2i) = Matrix2i(
            m00 = this.m00 * rhs.m00, m10 = this.m10 * rhs.m10,
            m01 = this.m01 * rhs.m01, m11 = this.m11 * rhs.m11
    )

    infix fun divComponents(rhs: Matrix2i) = Matrix2i(
            m00 = this.m00 / rhs.m00, m10 = this.m10 / rhs.m10,
            m01 = this.m01 / rhs.m01, m11 = this.m11 / rhs.m11
    )
    
    fun inverse() = Matrix2i(
            m00 = m11,  m10 = -m10,
            m01 = -m01, m11 = m00
    ) / determinant
    
    fun transpose() = Matrix2i(
            m00 = m00, m10 = m01,
            m01 = m10, m11 = m11
    )
    
    val determinant get() = m00 * m11 - m10 * m01
    
    fun lerp(to: Matrix2i, alpha: Double) = Matrix2i(
            m00 = m00.lerp(to.m00, alpha), m10 = m10.lerp(to.m10, alpha),
            m01 = m01.lerp(to.m01, alpha), m11 = m11.lerp(to.m11, alpha)
    )
    
    fun toDouble() = Matrix2d(
            m00 = m00.toDouble(), m10 = m10.toDouble(),
            m01 = m01.toDouble(), m11 = m11.toDouble()
    )

    fun toFloat() = Matrix2f(
            m00 = m00.toFloat(), m10 = m10.toFloat(),
            m01 = m01.toFloat(), m11 = m11.toFloat()
    )
    
    val row0 get() = Vector2i(m00, m10)
    val row1 get() = Vector2i(m01, m11)
    
    val column0 get() = Vector2i(m00, m01)
    val column1 get() = Vector2i(m10, m11)
}

operator fun Int.times(rhs: Matrix2i) = Matrix2i(
        m00 = this * rhs.m00, m10 = this * rhs.m10,
        m01 = this * rhs.m01, m11 = this * rhs.m11
)