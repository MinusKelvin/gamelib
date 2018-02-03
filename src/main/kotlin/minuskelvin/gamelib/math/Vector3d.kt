package minuskelvin.gamelib.math

import kotlin.math.sqrt

data class Vector3d(val x: Double, val y: Double, val z: Double) {
    constructor(xy: Vector2d, z: Double): this(xy.x, xy.y, z)
    constructor(x: Double, yz: Vector2d): this(x, yz.x, yz.y)
    
    operator fun plus(rhs: Vector3d) = Vector3d(
            x = this.x + rhs.x,
            y = this.y + rhs.y,
            z = this.z + rhs.z
    )

    operator fun minus(rhs: Vector3d) = Vector3d(
            x = this.x - rhs.x,
            y = this.y - rhs.y,
            z = this.z - rhs.z
    )

    operator fun times(rhs: Vector3d) = Vector3d(
            x = this.x * rhs.x,
            y = this.y * rhs.y,
            z = this.z * rhs.z
    )

    operator fun div(rhs: Vector3d) = Vector3d(
            x = this.x / rhs.x,
            y = this.y / rhs.y,
            z = this.z / rhs.z
    )

    operator fun times(rhs: Double) = Vector3d(
            x = this.x * rhs,
            y = this.y * rhs,
            z = this.z * rhs
    )

    operator fun Double.times(rhs: Vector3d) = Vector3d(
            x = this * rhs.x,
            y = this * rhs.y,
            z = this * rhs.z
    )

    operator fun div(rhs: Double) = Vector3d(
            x = this.x / rhs,
            y = this.y / rhs,
            z = this.z / rhs
    )

    infix fun dot(rhs: Vector3d) = this.x * rhs.x + this.y * rhs.y + this.z * rhs.z
    
    infix fun cross(rhs: Vector3d) = Vector3d(
            x = this.y * rhs.z - this.z * rhs.y,
            y = this.z * rhs.x - this.x * rhs.z,
            z = this.x * rhs.y - this.y * rhs.x
    )

    fun lerp(to: Vector3d, alpha: Double) = Vector3d(
            x = (1 - alpha) * x + alpha * to.x,
            y = (1 - alpha) * y + alpha * to.y,
            z = (1 - alpha) * z + alpha * to.z
    )

    fun toFloat() = Vector3f(
            x = x.toFloat(),
            y = y.toFloat(),
            z = z.toFloat()
    )

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    val unit get() = this / length

    val xx get() = Vector2d(x, x)
    val yx get() = Vector2d(y, x)
    val zx get() = Vector2d(z, x)
    val xy get() = Vector2d(x, y)
    val yy get() = Vector2d(y, y)
    val zy get() = Vector2d(z, y)
    val xz get() = Vector2d(x, z)
    val yz get() = Vector2d(y, z)
    val zz get() = Vector2d(z, z)

    val xxx get() = Vector3d(x, x, x)
    val yxx get() = Vector3d(y, x, x)
    val zxx get() = Vector3d(z, x, x)
    val xyx get() = Vector3d(x, y, x)
    val yyx get() = Vector3d(y, y, x)
    val zyx get() = Vector3d(z, y, x)
    val xzx get() = Vector3d(x, z, x)
    val yzx get() = Vector3d(y, z, x)
    val zzx get() = Vector3d(z, z, x)
    val xxy get() = Vector3d(x, x, y)
    val yxy get() = Vector3d(y, x, y)
    val zxy get() = Vector3d(z, x, y)
    val xyy get() = Vector3d(x, y, y)
    val yyy get() = Vector3d(y, y, y)
    val zyy get() = Vector3d(z, y, y)
    val xzy get() = Vector3d(x, z, y)
    val yzy get() = Vector3d(y, z, y)
    val zzy get() = Vector3d(z, z, y)
    val xxz get() = Vector3d(x, x, z)
    val yxz get() = Vector3d(y, x, z)
    val zxz get() = Vector3d(z, x, z)
    val xyz get() = this
    val yyz get() = Vector3d(y, y, z)
    val zyz get() = Vector3d(z, y, z)
    val xzz get() = Vector3d(x, z, z)
    val yzz get() = Vector3d(y, z, z)
    val zzz get() = Vector3d(z, z, z)

    val xxxx get() = Vector4d(x, x, x, x)
    val yxxx get() = Vector4d(y, x, x, x)
    val zxxx get() = Vector4d(z, x, x, x)
    val xyxx get() = Vector4d(x, y, x, x)
    val yyxx get() = Vector4d(y, y, x, x)
    val zyxx get() = Vector4d(z, y, x, x)
    val xzxx get() = Vector4d(x, z, x, x)
    val yzxx get() = Vector4d(y, z, x, x)
    val zzxx get() = Vector4d(z, z, x, x)
    val xxyx get() = Vector4d(x, x, y, x)
    val yxyx get() = Vector4d(y, x, y, x)
    val zxyx get() = Vector4d(z, x, y, x)
    val xyyx get() = Vector4d(x, y, y, x)
    val yyyx get() = Vector4d(y, y, y, x)
    val zyyx get() = Vector4d(z, y, y, x)
    val xzyx get() = Vector4d(x, z, y, x)
    val yzyx get() = Vector4d(y, z, y, x)
    val zzyx get() = Vector4d(z, z, y, x)
    val xxzx get() = Vector4d(x, x, z, x)
    val yxzx get() = Vector4d(y, x, z, x)
    val zxzx get() = Vector4d(z, x, z, x)
    val xyzx get() = Vector4d(x, y, z, x)
    val yyzx get() = Vector4d(y, y, z, x)
    val zyzx get() = Vector4d(z, y, z, x)
    val xzzx get() = Vector4d(x, z, z, x)
    val yzzx get() = Vector4d(y, z, z, x)
    val zzzx get() = Vector4d(z, z, z, x)
    val xxxy get() = Vector4d(x, x, x, y)
    val yxxy get() = Vector4d(y, x, x, y)
    val zxxy get() = Vector4d(z, x, x, y)
    val xyxy get() = Vector4d(x, y, x, y)
    val yyxy get() = Vector4d(y, y, x, y)
    val zyxy get() = Vector4d(z, y, x, y)
    val xzxy get() = Vector4d(x, z, x, y)
    val yzxy get() = Vector4d(y, z, x, y)
    val zzxy get() = Vector4d(z, z, x, y)
    val xxyy get() = Vector4d(x, x, y, y)
    val yxyy get() = Vector4d(y, x, y, y)
    val zxyy get() = Vector4d(z, x, y, y)
    val xyyy get() = Vector4d(x, y, y, y)
    val yyyy get() = Vector4d(y, y, y, y)
    val zyyy get() = Vector4d(z, y, y, y)
    val xzyy get() = Vector4d(x, z, y, y)
    val yzyy get() = Vector4d(y, z, y, y)
    val zzyy get() = Vector4d(z, z, y, y)
    val xxzy get() = Vector4d(x, x, z, y)
    val yxzy get() = Vector4d(y, x, z, y)
    val zxzy get() = Vector4d(z, x, z, y)
    val xyzy get() = Vector4d(x, y, z, y)
    val yyzy get() = Vector4d(y, y, z, y)
    val zyzy get() = Vector4d(z, y, z, y)
    val xzzy get() = Vector4d(x, z, z, y)
    val yzzy get() = Vector4d(y, z, z, y)
    val zzzy get() = Vector4d(z, z, z, y)
    val xxxz get() = Vector4d(x, x, x, z)
    val yxxz get() = Vector4d(y, x, x, z)
    val zxxz get() = Vector4d(z, x, x, z)
    val xyxz get() = Vector4d(x, y, x, z)
    val yyxz get() = Vector4d(y, y, x, z)
    val zyxz get() = Vector4d(z, y, x, z)
    val xzxz get() = Vector4d(x, z, x, z)
    val yzxz get() = Vector4d(y, z, x, z)
    val zzxz get() = Vector4d(z, z, x, z)
    val xxyz get() = Vector4d(x, x, y, z)
    val yxyz get() = Vector4d(y, x, y, z)
    val zxyz get() = Vector4d(z, x, y, z)
    val xyyz get() = Vector4d(x, y, y, z)
    val yyyz get() = Vector4d(y, y, y, z)
    val zyyz get() = Vector4d(z, y, y, z)
    val xzyz get() = Vector4d(x, z, y, z)
    val yzyz get() = Vector4d(y, z, y, z)
    val zzyz get() = Vector4d(z, z, y, z)
    val xxzz get() = Vector4d(x, x, z, z)
    val yxzz get() = Vector4d(y, x, z, z)
    val zxzz get() = Vector4d(z, x, z, z)
    val xyzz get() = Vector4d(x, y, z, z)
    val yyzz get() = Vector4d(y, y, z, z)
    val zyzz get() = Vector4d(z, y, z, z)
    val xzzz get() = Vector4d(x, z, z, z)
    val yzzz get() = Vector4d(y, z, z, z)
    val zzzz get() = Vector4d(z, z, z, z)
}