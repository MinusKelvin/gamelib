package minuskelvin.gamelib.math.matrix

import minuskelvin.gamelib.math.lerp
import minuskelvin.gamelib.math.vector.Vector2f

/**
 * ```
 * [ m00 m10 ]
 * [ m01 m11 ]
 * ```
 */
data class Matrix2f(
        val m00: Float, val m01: Float, // Column 0
        val m10: Float, val m11: Float  // Column 1
) {
    constructor(): this(
            m00 = 1f, m10 = 0f,
            m01 = 0f, m11 = 1f
    )
    
    constructor(column0: Vector2f, column1: Vector2f): this(
            m00 = column0.x, m10 = column1.x,
            m01 = column0.y, m11 = column1.y
    )
    
    operator fun plus(rhs: Matrix2f) = Matrix2f(
            m00 = this.m00 + rhs.m00, m10 = this.m10 + rhs.m10,
            m01 = this.m01 + rhs.m01, m11 = this.m11 + rhs.m11
    )
    
    operator fun minus(rhs: Matrix2f) = Matrix2f(
            m00 = this.m00 - rhs.m00, m10 = this.m10 - rhs.m10,
            m01 = this.m01 - rhs.m01, m11 = this.m11 - rhs.m11
    )
    
    operator fun times(rhs: Matrix2f) = Matrix2f(
            m00 = this.row0 dot rhs.column0, m10 = this.row1 dot rhs.column0,
            m01 = this.row0 dot rhs.column1, m11 = this.row1 dot rhs.column1
    )
    
    operator fun times(rhs: Float) = Matrix2f(
            m00 = m00 * rhs, m10 = m10 * rhs,
            m01 = m01 * rhs, m11 = m11 * rhs
    )
    
    operator fun div(rhs: Float) = Matrix2f(
            m00 = m00 / rhs, m10 = m10 / rhs,
            m01 = m01 / rhs, m11 = m11 / rhs
    )
    
    infix fun mulComponents(rhs: Matrix2f) = Matrix2f(
            m00 = this.m00 * rhs.m00, m10 = this.m10 * rhs.m10,
            m01 = this.m01 * rhs.m01, m11 = this.m11 * rhs.m11
    )

    infix fun divComponents(rhs: Matrix2f) = Matrix2f(
            m00 = this.m00 / rhs.m00, m10 = this.m10 / rhs.m10,
            m01 = this.m01 / rhs.m01, m11 = this.m11 / rhs.m11
    )
    
    fun invert() = Matrix2f(
            m00 = m11,  m10 = -m10,
            m01 = -m01, m11 = m00
    ) / determinant
    
    fun transpose() = Matrix2f(
            m00 = m00, m10 = m01,
            m01 = m10, m11 = m11
    )
    
    val determinant get() = m00 * m11 - m10 * m01
    
    fun lerp(to: Matrix2f, alpha: Float) = Matrix2f(
            m00 = m00.lerp(to.m00, alpha), m10 = m10.lerp(to.m10, alpha),
            m01 = m01.lerp(to.m01, alpha), m11 = m11.lerp(to.m11, alpha)
    )
    
    val row0 get() = Vector2f(m00, m10)
    val row1 get() = Vector2f(m01, m11)
    
    val column0 get() = Vector2f(m00, m01)
    val column1 get() = Vector2f(m10, m11)
}

operator fun Float.times(rhs: Matrix2f) = Matrix2f(
        m00 = this * rhs.m00, m10 = this * rhs.m10,
        m01 = this * rhs.m01, m11 = this * rhs.m11
)