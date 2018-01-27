package minuskelvin.gamelib.math

import kotlin.math.sqrt

data class Vector3f(val x: Float, val y: Float, val z: Float) {
    constructor(xy: Vector2f, z: Float): this(xy.x, xy.y, z)
    constructor(x: Float, yz: Vector2f): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3f) = Vector3f(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3f) = Vector3f(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3f) = Vector3f(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3f) = Vector3f(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Float) = Vector3f(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun Float.times(rhs: Vector3f) = Vector3f(
            x = this * rhs.x,
            y = this * rhs.y,
            z = this * rhs.z
    )

    operator fun div(rhs: Float) = Vector3f(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    infix fun dot(rhs: Vector3f) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3f) = Vector3f(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )

    fun toDouble() = Vector3d(
            x = x.toDouble(),
            y = y.toDouble(),
            z = z.toDouble()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length

    val xx get() = Vector2f(x, x)
    val yx get() = Vector2f(y, x)
    val zx get() = Vector2f(z, x)
    val xy get() = Vector2f(x, y)
    val yy get() = Vector2f(y, y)
    val zy get() = Vector2f(z, y)
    val xz get() = Vector2f(x, z)
    val yz get() = Vector2f(y, z)
    val zz get() = Vector2f(z, z)

    val xxx get() = Vector3f(x, x, x)
    val yxx get() = Vector3f(y, x, x)
    val zxx get() = Vector3f(z, x, x)
    val xyx get() = Vector3f(x, y, x)
    val yyx get() = Vector3f(y, y, x)
    val zyx get() = Vector3f(z, y, x)
    val xzx get() = Vector3f(x, z, x)
    val yzx get() = Vector3f(y, z, x)
    val zzx get() = Vector3f(z, z, x)
    val xxy get() = Vector3f(x, x, y)
    val yxy get() = Vector3f(y, x, y)
    val zxy get() = Vector3f(z, x, y)
    val xyy get() = Vector3f(x, y, y)
    val yyy get() = Vector3f(y, y, y)
    val zyy get() = Vector3f(z, y, y)
    val xzy get() = Vector3f(x, z, y)
    val yzy get() = Vector3f(y, z, y)
    val zzy get() = Vector3f(z, z, y)
    val xxz get() = Vector3f(x, x, z)
    val yxz get() = Vector3f(y, x, z)
    val zxz get() = Vector3f(z, x, z)
    val xyz get() = this
    val yyz get() = Vector3f(y, y, z)
    val zyz get() = Vector3f(z, y, z)
    val xzz get() = Vector3f(x, z, z)
    val yzz get() = Vector3f(y, z, z)
    val zzz get() = Vector3f(z, z, z)

    val xxxx get() = Vector4f(x, x, x, x)
    val yxxx get() = Vector4f(y, x, x, x)
    val zxxx get() = Vector4f(z, x, x, x)
    val xyxx get() = Vector4f(x, y, x, x)
    val yyxx get() = Vector4f(y, y, x, x)
    val zyxx get() = Vector4f(z, y, x, x)
    val xzxx get() = Vector4f(x, z, x, x)
    val yzxx get() = Vector4f(y, z, x, x)
    val zzxx get() = Vector4f(z, z, x, x)
    val xxyx get() = Vector4f(x, x, y, x)
    val yxyx get() = Vector4f(y, x, y, x)
    val zxyx get() = Vector4f(z, x, y, x)
    val xyyx get() = Vector4f(x, y, y, x)
    val yyyx get() = Vector4f(y, y, y, x)
    val zyyx get() = Vector4f(z, y, y, x)
    val xzyx get() = Vector4f(x, z, y, x)
    val yzyx get() = Vector4f(y, z, y, x)
    val zzyx get() = Vector4f(z, z, y, x)
    val xxzx get() = Vector4f(x, x, z, x)
    val yxzx get() = Vector4f(y, x, z, x)
    val zxzx get() = Vector4f(z, x, z, x)
    val xyzx get() = Vector4f(x, y, z, x)
    val yyzx get() = Vector4f(y, y, z, x)
    val zyzx get() = Vector4f(z, y, z, x)
    val xzzx get() = Vector4f(x, z, z, x)
    val yzzx get() = Vector4f(y, z, z, x)
    val zzzx get() = Vector4f(z, z, z, x)
    val xxxy get() = Vector4f(x, x, x, y)
    val yxxy get() = Vector4f(y, x, x, y)
    val zxxy get() = Vector4f(z, x, x, y)
    val xyxy get() = Vector4f(x, y, x, y)
    val yyxy get() = Vector4f(y, y, x, y)
    val zyxy get() = Vector4f(z, y, x, y)
    val xzxy get() = Vector4f(x, z, x, y)
    val yzxy get() = Vector4f(y, z, x, y)
    val zzxy get() = Vector4f(z, z, x, y)
    val xxyy get() = Vector4f(x, x, y, y)
    val yxyy get() = Vector4f(y, x, y, y)
    val zxyy get() = Vector4f(z, x, y, y)
    val xyyy get() = Vector4f(x, y, y, y)
    val yyyy get() = Vector4f(y, y, y, y)
    val zyyy get() = Vector4f(z, y, y, y)
    val xzyy get() = Vector4f(x, z, y, y)
    val yzyy get() = Vector4f(y, z, y, y)
    val zzyy get() = Vector4f(z, z, y, y)
    val xxzy get() = Vector4f(x, x, z, y)
    val yxzy get() = Vector4f(y, x, z, y)
    val zxzy get() = Vector4f(z, x, z, y)
    val xyzy get() = Vector4f(x, y, z, y)
    val yyzy get() = Vector4f(y, y, z, y)
    val zyzy get() = Vector4f(z, y, z, y)
    val xzzy get() = Vector4f(x, z, z, y)
    val yzzy get() = Vector4f(y, z, z, y)
    val zzzy get() = Vector4f(z, z, z, y)
    val xxxz get() = Vector4f(x, x, x, z)
    val yxxz get() = Vector4f(y, x, x, z)
    val zxxz get() = Vector4f(z, x, x, z)
    val xyxz get() = Vector4f(x, y, x, z)
    val yyxz get() = Vector4f(y, y, x, z)
    val zyxz get() = Vector4f(z, y, x, z)
    val xzxz get() = Vector4f(x, z, x, z)
    val yzxz get() = Vector4f(y, z, x, z)
    val zzxz get() = Vector4f(z, z, x, z)
    val xxyz get() = Vector4f(x, x, y, z)
    val yxyz get() = Vector4f(y, x, y, z)
    val zxyz get() = Vector4f(z, x, y, z)
    val xyyz get() = Vector4f(x, y, y, z)
    val yyyz get() = Vector4f(y, y, y, z)
    val zyyz get() = Vector4f(z, y, y, z)
    val xzyz get() = Vector4f(x, z, y, z)
    val yzyz get() = Vector4f(y, z, y, z)
    val zzyz get() = Vector4f(z, z, y, z)
    val xxzz get() = Vector4f(x, x, z, z)
    val yxzz get() = Vector4f(y, x, z, z)
    val zxzz get() = Vector4f(z, x, z, z)
    val xyzz get() = Vector4f(x, y, z, z)
    val yyzz get() = Vector4f(y, y, z, z)
    val zyzz get() = Vector4f(z, y, z, z)
    val xzzz get() = Vector4f(x, z, z, z)
    val yzzz get() = Vector4f(y, z, z, z)
    val zzzz get() = Vector4f(z, z, z, z)
}