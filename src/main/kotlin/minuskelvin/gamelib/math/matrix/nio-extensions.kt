package minuskelvin.gamelib.math.matrix

import java.nio.ByteBuffer

fun ByteBuffer.putMatrix(matrix: Matrix2f) {
    putFloat(matrix.m00)
    putFloat(matrix.m01)
    putFloat(matrix.m10)
    putFloat(matrix.m11)
}
fun ByteBuffer.putMatrix(matrix: Matrix3f) {
    putFloat(matrix.m00)
    putFloat(matrix.m01)
    putFloat(matrix.m02)
    putFloat(matrix.m10)
    putFloat(matrix.m11)
    putFloat(matrix.m12)
    putFloat(matrix.m20)
    putFloat(matrix.m21)
    putFloat(matrix.m22)
}
fun ByteBuffer.putMatrix(matrix: Matrix4f) {
    putFloat(matrix.m00)
    putFloat(matrix.m01)
    putFloat(matrix.m02)
    putFloat(matrix.m03)
    putFloat(matrix.m10)
    putFloat(matrix.m11)
    putFloat(matrix.m12)
    putFloat(matrix.m13)
    putFloat(matrix.m20)
    putFloat(matrix.m21)
    putFloat(matrix.m22)
    putFloat(matrix.m23)
    putFloat(matrix.m30)
    putFloat(matrix.m31)
    putFloat(matrix.m32)
    putFloat(matrix.m33)
}

fun ByteBuffer.putMatrix(index: Int, matrix: Matrix2f) {
    putFloat(index + 0, matrix.m00)
    putFloat(index + 4, matrix.m01)
    putFloat(index + 8, matrix.m10)
    putFloat(index + 12, matrix.m11)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix3f) {
    putFloat(index + 0, matrix.m00)
    putFloat(index + 4, matrix.m01)
    putFloat(index + 8, matrix.m02)
    putFloat(index + 12, matrix.m10)
    putFloat(index + 16, matrix.m11)
    putFloat(index + 20, matrix.m12)
    putFloat(index + 24, matrix.m20)
    putFloat(index + 28, matrix.m21)
    putFloat(index + 32, matrix.m22)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix4f) {
    putFloat(index + 0, matrix.m00)
    putFloat(index + 4, matrix.m01)
    putFloat(index + 8, matrix.m02)
    putFloat(index + 12, matrix.m03)
    putFloat(index + 16, matrix.m10)
    putFloat(index + 20, matrix.m11)
    putFloat(index + 24, matrix.m12)
    putFloat(index + 28, matrix.m13)
    putFloat(index + 32, matrix.m20)
    putFloat(index + 36, matrix.m21)
    putFloat(index + 40, matrix.m22)
    putFloat(index + 44, matrix.m23)
    putFloat(index + 48, matrix.m30)
    putFloat(index + 52, matrix.m31)
    putFloat(index + 56, matrix.m32)
    putFloat(index + 60, matrix.m33)
}

fun ByteBuffer.putMatrix(matrix: Matrix2i) {
    putInt(matrix.m00)
    putInt(matrix.m01)
    putInt(matrix.m10)
    putInt(matrix.m11)
}
fun ByteBuffer.putMatrix(matrix: Matrix3i) {
    putInt(matrix.m00)
    putInt(matrix.m01)
    putInt(matrix.m02)
    putInt(matrix.m10)
    putInt(matrix.m11)
    putInt(matrix.m12)
    putInt(matrix.m20)
    putInt(matrix.m21)
    putInt(matrix.m22)
}
fun ByteBuffer.putMatrix(matrix: Matrix4i) {
    putInt(matrix.m00)
    putInt(matrix.m01)
    putInt(matrix.m02)
    putInt(matrix.m03)
    putInt(matrix.m10)
    putInt(matrix.m11)
    putInt(matrix.m12)
    putInt(matrix.m13)
    putInt(matrix.m20)
    putInt(matrix.m21)
    putInt(matrix.m22)
    putInt(matrix.m23)
    putInt(matrix.m30)
    putInt(matrix.m31)
    putInt(matrix.m32)
    putInt(matrix.m33)
}

fun ByteBuffer.putMatrix(index: Int, matrix: Matrix2i) {
    putInt(index + 0, matrix.m00)
    putInt(index + 4, matrix.m01)
    putInt(index + 8, matrix.m10)
    putInt(index + 12, matrix.m11)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix3i) {
    putInt(index + 0, matrix.m00)
    putInt(index + 4, matrix.m01)
    putInt(index + 8, matrix.m02)
    putInt(index + 12, matrix.m10)
    putInt(index + 16, matrix.m11)
    putInt(index + 20, matrix.m12)
    putInt(index + 24, matrix.m20)
    putInt(index + 28, matrix.m21)
    putInt(index + 32, matrix.m22)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix4i) {
    putInt(index + 0, matrix.m00)
    putInt(index + 4, matrix.m01)
    putInt(index + 8, matrix.m02)
    putInt(index + 12, matrix.m03)
    putInt(index + 16, matrix.m10)
    putInt(index + 20, matrix.m11)
    putInt(index + 24, matrix.m12)
    putInt(index + 28, matrix.m13)
    putInt(index + 32, matrix.m20)
    putInt(index + 36, matrix.m21)
    putInt(index + 40, matrix.m22)
    putInt(index + 44, matrix.m23)
    putInt(index + 48, matrix.m30)
    putInt(index + 52, matrix.m31)
    putInt(index + 56, matrix.m32)
    putInt(index + 60, matrix.m33)
}

fun ByteBuffer.putMatrix(matrix: Matrix2d) {
    putDouble(matrix.m00)
    putDouble(matrix.m01)
    putDouble(matrix.m10)
    putDouble(matrix.m11)
}
fun ByteBuffer.putMatrix(matrix: Matrix3d) {
    putDouble(matrix.m00)
    putDouble(matrix.m01)
    putDouble(matrix.m02)
    putDouble(matrix.m10)
    putDouble(matrix.m11)
    putDouble(matrix.m12)
    putDouble(matrix.m20)
    putDouble(matrix.m21)
    putDouble(matrix.m22)
}
fun ByteBuffer.putMatrix(matrix: Matrix4d) {
    putDouble(matrix.m00)
    putDouble(matrix.m01)
    putDouble(matrix.m02)
    putDouble(matrix.m03)
    putDouble(matrix.m10)
    putDouble(matrix.m11)
    putDouble(matrix.m12)
    putDouble(matrix.m13)
    putDouble(matrix.m20)
    putDouble(matrix.m21)
    putDouble(matrix.m22)
    putDouble(matrix.m23)
    putDouble(matrix.m30)
    putDouble(matrix.m31)
    putDouble(matrix.m32)
    putDouble(matrix.m33)
}

fun ByteBuffer.putMatrix(index: Int, matrix: Matrix2d) {
    putDouble(index + 0, matrix.m00)
    putDouble(index + 8, matrix.m01)
    putDouble(index + 16, matrix.m10)
    putDouble(index + 24, matrix.m11)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix3d) {
    putDouble(index + 0, matrix.m00)
    putDouble(index + 8, matrix.m01)
    putDouble(index + 16, matrix.m02)
    putDouble(index + 24, matrix.m10)
    putDouble(index + 32, matrix.m11)
    putDouble(index + 40, matrix.m12)
    putDouble(index + 48, matrix.m20)
    putDouble(index + 56, matrix.m21)
    putDouble(index + 64, matrix.m22)
}
fun ByteBuffer.putMatrix(index: Int, matrix: Matrix4d) {
    putDouble(index + 0, matrix.m00)
    putDouble(index + 8, matrix.m01)
    putDouble(index + 16, matrix.m02)
    putDouble(index + 24, matrix.m03)
    putDouble(index + 32, matrix.m10)
    putDouble(index + 40, matrix.m11)
    putDouble(index + 48, matrix.m12)
    putDouble(index + 56, matrix.m13)
    putDouble(index + 64, matrix.m20)
    putDouble(index + 72, matrix.m21)
    putDouble(index + 80, matrix.m22)
    putDouble(index + 88, matrix.m23)
    putDouble(index + 96, matrix.m30)
    putDouble(index + 104, matrix.m31)
    putDouble(index + 112, matrix.m32)
    putDouble(index + 120, matrix.m33)
}

fun ByteBuffer.getMatrix2f(index: Int) = Matrix2f(
        getFloat(index + 0),
        getFloat(index + 4),
        getFloat(index + 8),
        getFloat(index + 12)
)
fun ByteBuffer.getMatrix3f(index: Int) = Matrix3f(
        getFloat(index + 0),
        getFloat(index + 4),
        getFloat(index + 8),
        getFloat(index + 12),
        getFloat(index + 16),
        getFloat(index + 20),
        getFloat(index + 24),
        getFloat(index + 28),
        getFloat(index + 32)
)
fun ByteBuffer.getMatrix4f(index: Int) = Matrix4f(
        getFloat(index + 0),
        getFloat(index + 4),
        getFloat(index + 8),
        getFloat(index + 12),
        getFloat(index + 16),
        getFloat(index + 20),
        getFloat(index + 24),
        getFloat(index + 28),
        getFloat(index + 32),
        getFloat(index + 36),
        getFloat(index + 40),
        getFloat(index + 44),
        getFloat(index + 48),
        getFloat(index + 52),
        getFloat(index + 56),
        getFloat(index + 60)
)

fun ByteBuffer.getMatrix2i(index: Int) = Matrix2i(
        getInt(index + 0),
        getInt(index + 4),
        getInt(index + 8),
        getInt(index + 12)
)
fun ByteBuffer.getMatrix3i(index: Int) = Matrix3i(
        getInt(index + 0),
        getInt(index + 4),
        getInt(index + 8),
        getInt(index + 12),
        getInt(index + 16),
        getInt(index + 20),
        getInt(index + 24),
        getInt(index + 28),
        getInt(index + 32)
)
fun ByteBuffer.getMatrix4i(index: Int) = Matrix4i(
        getInt(index + 0),
        getInt(index + 4),
        getInt(index + 8),
        getInt(index + 12),
        getInt(index + 16),
        getInt(index + 20),
        getInt(index + 24),
        getInt(index + 28),
        getInt(index + 32),
        getInt(index + 36),
        getInt(index + 40),
        getInt(index + 44),
        getInt(index + 48),
        getInt(index + 52),
        getInt(index + 56),
        getInt(index + 60)
)

fun ByteBuffer.getMatrix2d(index: Int) = Matrix2d(
        getDouble(index + 0),
        getDouble(index + 8),
        getDouble(index + 16),
        getDouble(index + 24)
)
fun ByteBuffer.getMatrix3d(index: Int) = Matrix3d(
        getDouble(index + 0),
        getDouble(index + 8),
        getDouble(index + 16),
        getDouble(index + 24),
        getDouble(index + 32),
        getDouble(index + 40),
        getDouble(index + 40),
        getDouble(index + 56),
        getDouble(index + 64)
)
fun ByteBuffer.getMatrix4d(index: Int) = Matrix4d(
        getDouble(index + 0),
        getDouble(index + 8),
        getDouble(index + 16),
        getDouble(index + 24),
        getDouble(index + 32),
        getDouble(index + 40),
        getDouble(index + 48),
        getDouble(index + 56),
        getDouble(index + 64),
        getDouble(index + 72),
        getDouble(index + 80),
        getDouble(index + 88),
        getDouble(index + 96),
        getDouble(index + 104),
        getDouble(index + 112),
        getDouble(index + 120)
)