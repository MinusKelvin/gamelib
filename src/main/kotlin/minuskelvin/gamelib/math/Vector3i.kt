package minuskelvin.gamelib.math

import kotlin.math.sqrt

data class Vector3i(val x: Int, val y: Int, val z: Int) {
    constructor(xy: Vector2i, z: Int): this(xy.x, xy.y, z)
    constructor(x: Int, yz: Vector2i): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3i) = Vector3i(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3i) = Vector3i(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3i) = Vector3i(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3i) = Vector3i(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Int) = Vector3i(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun Int.times(rhs: Vector3i) = Vector3i(
            x = this * rhs.x,
            y = this * rhs.y,
            z = this * rhs.z
    )

    operator fun div(rhs: Int) = Vector3i(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    infix fun dot(rhs: Vector3i) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3i) = Vector3i(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )
    
    fun toDouble() = Vector3d(
            x = x.toDouble(),
            y = y.toDouble(),
            z = z.toDouble()
    )

    fun toFloat() = Vector3f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq.toDouble())

    val xx get() = Vector2i(x, x)
    val yx get() = Vector2i(y, x)
    val zx get() = Vector2i(z, x)
    val xy get() = Vector2i(x, y)
    val yy get() = Vector2i(y, y)
    val zy get() = Vector2i(z, y)
    val xz get() = Vector2i(x, z)
    val yz get() = Vector2i(y, z)
    val zz get() = Vector2i(z, z)

    val xxx get() = Vector3i(x, x, x)
    val yxx get() = Vector3i(y, x, x)
    val zxx get() = Vector3i(z, x, x)
    val xyx get() = Vector3i(x, y, x)
    val yyx get() = Vector3i(y, y, x)
    val zyx get() = Vector3i(z, y, x)
    val xzx get() = Vector3i(x, z, x)
    val yzx get() = Vector3i(y, z, x)
    val zzx get() = Vector3i(z, z, x)
    val xxy get() = Vector3i(x, x, y)
    val yxy get() = Vector3i(y, x, y)
    val zxy get() = Vector3i(z, x, y)
    val xyy get() = Vector3i(x, y, y)
    val yyy get() = Vector3i(y, y, y)
    val zyy get() = Vector3i(z, y, y)
    val xzy get() = Vector3i(x, z, y)
    val yzy get() = Vector3i(y, z, y)
    val zzy get() = Vector3i(z, z, y)
    val xxz get() = Vector3i(x, x, z)
    val yxz get() = Vector3i(y, x, z)
    val zxz get() = Vector3i(z, x, z)
    val xyz get() = this
    val yyz get() = Vector3i(y, y, z)
    val zyz get() = Vector3i(z, y, z)
    val xzz get() = Vector3i(x, z, z)
    val yzz get() = Vector3i(y, z, z)
    val zzz get() = Vector3i(z, z, z)

    val xxxx get() = Vector4i(x, x, x, x)
    val yxxx get() = Vector4i(y, x, x, x)
    val zxxx get() = Vector4i(z, x, x, x)
    val xyxx get() = Vector4i(x, y, x, x)
    val yyxx get() = Vector4i(y, y, x, x)
    val zyxx get() = Vector4i(z, y, x, x)
    val xzxx get() = Vector4i(x, z, x, x)
    val yzxx get() = Vector4i(y, z, x, x)
    val zzxx get() = Vector4i(z, z, x, x)
    val xxyx get() = Vector4i(x, x, y, x)
    val yxyx get() = Vector4i(y, x, y, x)
    val zxyx get() = Vector4i(z, x, y, x)
    val xyyx get() = Vector4i(x, y, y, x)
    val yyyx get() = Vector4i(y, y, y, x)
    val zyyx get() = Vector4i(z, y, y, x)
    val xzyx get() = Vector4i(x, z, y, x)
    val yzyx get() = Vector4i(y, z, y, x)
    val zzyx get() = Vector4i(z, z, y, x)
    val xxzx get() = Vector4i(x, x, z, x)
    val yxzx get() = Vector4i(y, x, z, x)
    val zxzx get() = Vector4i(z, x, z, x)
    val xyzx get() = Vector4i(x, y, z, x)
    val yyzx get() = Vector4i(y, y, z, x)
    val zyzx get() = Vector4i(z, y, z, x)
    val xzzx get() = Vector4i(x, z, z, x)
    val yzzx get() = Vector4i(y, z, z, x)
    val zzzx get() = Vector4i(z, z, z, x)
    val xxxy get() = Vector4i(x, x, x, y)
    val yxxy get() = Vector4i(y, x, x, y)
    val zxxy get() = Vector4i(z, x, x, y)
    val xyxy get() = Vector4i(x, y, x, y)
    val yyxy get() = Vector4i(y, y, x, y)
    val zyxy get() = Vector4i(z, y, x, y)
    val xzxy get() = Vector4i(x, z, x, y)
    val yzxy get() = Vector4i(y, z, x, y)
    val zzxy get() = Vector4i(z, z, x, y)
    val xxyy get() = Vector4i(x, x, y, y)
    val yxyy get() = Vector4i(y, x, y, y)
    val zxyy get() = Vector4i(z, x, y, y)
    val xyyy get() = Vector4i(x, y, y, y)
    val yyyy get() = Vector4i(y, y, y, y)
    val zyyy get() = Vector4i(z, y, y, y)
    val xzyy get() = Vector4i(x, z, y, y)
    val yzyy get() = Vector4i(y, z, y, y)
    val zzyy get() = Vector4i(z, z, y, y)
    val xxzy get() = Vector4i(x, x, z, y)
    val yxzy get() = Vector4i(y, x, z, y)
    val zxzy get() = Vector4i(z, x, z, y)
    val xyzy get() = Vector4i(x, y, z, y)
    val yyzy get() = Vector4i(y, y, z, y)
    val zyzy get() = Vector4i(z, y, z, y)
    val xzzy get() = Vector4i(x, z, z, y)
    val yzzy get() = Vector4i(y, z, z, y)
    val zzzy get() = Vector4i(z, z, z, y)
    val xxxz get() = Vector4i(x, x, x, z)
    val yxxz get() = Vector4i(y, x, x, z)
    val zxxz get() = Vector4i(z, x, x, z)
    val xyxz get() = Vector4i(x, y, x, z)
    val yyxz get() = Vector4i(y, y, x, z)
    val zyxz get() = Vector4i(z, y, x, z)
    val xzxz get() = Vector4i(x, z, x, z)
    val yzxz get() = Vector4i(y, z, x, z)
    val zzxz get() = Vector4i(z, z, x, z)
    val xxyz get() = Vector4i(x, x, y, z)
    val yxyz get() = Vector4i(y, x, y, z)
    val zxyz get() = Vector4i(z, x, y, z)
    val xyyz get() = Vector4i(x, y, y, z)
    val yyyz get() = Vector4i(y, y, y, z)
    val zyyz get() = Vector4i(z, y, y, z)
    val xzyz get() = Vector4i(x, z, y, z)
    val yzyz get() = Vector4i(y, z, y, z)
    val zzyz get() = Vector4i(z, z, y, z)
    val xxzz get() = Vector4i(x, x, z, z)
    val yxzz get() = Vector4i(y, x, z, z)
    val zxzz get() = Vector4i(z, x, z, z)
    val xyzz get() = Vector4i(x, y, z, z)
    val yyzz get() = Vector4i(y, y, z, z)
    val zyzz get() = Vector4i(z, y, z, z)
    val xzzz get() = Vector4i(x, z, z, z)
    val yzzz get() = Vector4i(y, z, z, z)
    val zzzz get() = Vector4i(z, z, z, z)
}