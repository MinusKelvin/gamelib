package minuskelvin.gamelib.math.vector

import minuskelvin.gamelib.math.toIntUnsigned
import java.nio.ByteBuffer

fun ByteBuffer.putVectorAsByte(vector: Vector2i) {
    put(vector.x.toByte())
    put(vector.y.toByte())
}
fun ByteBuffer.putVectorAsByte(vector: Vector3i) {
    put(vector.x.toByte())
    put(vector.y.toByte())
    put(vector.z.toByte())
}
fun ByteBuffer.putVectorAsByte(vector: Vector4i) {
    put(vector.x.toByte())
    put(vector.y.toByte())
    put(vector.z.toByte())
    put(vector.w.toByte())
}
fun ByteBuffer.putVectorAsByte(index: Int, vector: Vector2i) {
    put(index+0, vector.x.toByte())
    put(index+1, vector.y.toByte())
}
fun ByteBuffer.putVectorAsByte(index: Int, vector: Vector3i) {
    put(index+0, vector.x.toByte())
    put(index+1, vector.y.toByte())
    put(index+2, vector.z.toByte())
}
fun ByteBuffer.putVectorAsByte(index: Int, vector: Vector4i) {
    put(index+0, vector.x.toByte())
    put(index+1, vector.y.toByte())
    put(index+2, vector.z.toByte())
    put(index+3, vector.w.toByte())
}

fun ByteBuffer.putVectorAsShort(vector: Vector2i) {
    putShort(vector.x.toShort())
    putShort(vector.y.toShort())
}
fun ByteBuffer.putVectorAsShort(vector: Vector3i) {
    putShort(vector.x.toShort())
    putShort(vector.y.toShort())
    putShort(vector.z.toShort())
}
fun ByteBuffer.putVectorAsShort(vector: Vector4i) {
    putShort(vector.x.toShort())
    putShort(vector.y.toShort())
    putShort(vector.z.toShort())
    putShort(vector.w.toShort())
}
fun ByteBuffer.putVectorAsShort(index: Int, vector: Vector2i) {
    putShort(index+0, vector.x.toShort())
    putShort(index+2, vector.y.toShort())
}
fun ByteBuffer.putVectorAsShort(index: Int, vector: Vector3i) {
    putShort(index+0, vector.x.toShort())
    putShort(index+2, vector.y.toShort())
    putShort(index+4, vector.z.toShort())
}
fun ByteBuffer.putVectorAsShort(index: Int, vector: Vector4i) {
    putShort(index+0, vector.x.toShort())
    putShort(index+2, vector.y.toShort())
    putShort(index+4, vector.z.toShort())
    putShort(index+6, vector.w.toShort())
}

fun ByteBuffer.putVector(vector: Vector2i) {
    putInt(vector.x)
    putInt(vector.y)
}
fun ByteBuffer.putVector(vector: Vector3i) {
    putInt(vector.x)
    putInt(vector.y)
    putInt(vector.z)
}
fun ByteBuffer.putVector(vector: Vector4i) {
    putInt(vector.x)
    putInt(vector.y)
    putInt(vector.z)
    putInt(vector.w)
}
fun ByteBuffer.putVector(index: Int, vector: Vector2i) {
    putInt(index+0, vector.x)
    putInt(index+4, vector.y)
}
fun ByteBuffer.putVector(index: Int, vector: Vector3i) {
    putInt(index+0, vector.x)
    putInt(index+4, vector.y)
    putInt(index+8, vector.z)
}
fun ByteBuffer.putVector(index: Int, vector: Vector4i) {
    putInt(index+0, vector.x)
    putInt(index+4, vector.y)
    putInt(index+8, vector.z)
    putInt(index+12, vector.w)
}

fun ByteBuffer.putVector(vector: Vector2f) {
    putFloat(vector.x)
    putFloat(vector.y)
}
fun ByteBuffer.putVector(vector: Vector3f) {
    putFloat(vector.x)
    putFloat(vector.y)
    putFloat(vector.z)
}
fun ByteBuffer.putVector(vector: Vector4f) {
    putFloat(vector.x)
    putFloat(vector.y)
    putFloat(vector.z)
    putFloat(vector.w)
}
fun ByteBuffer.putVector(index: Int, vector: Vector2f) {
    putFloat(index+0, vector.x)
    putFloat(index+4, vector.y)
}
fun ByteBuffer.putVector(index: Int, vector: Vector3f) {
    putFloat(index+0, vector.x)
    putFloat(index+4, vector.y)
    putFloat(index+8, vector.z)
}
fun ByteBuffer.putVector(index: Int, vector: Vector4f) {
    putFloat(index+0, vector.x)
    putFloat(index+4, vector.y)
    putFloat(index+8, vector.z)
    putFloat(index+12, vector.w)
}

fun ByteBuffer.putVector(vector: Vector2d) {
    putDouble(vector.x)
    putDouble(vector.y)
}
fun ByteBuffer.putVector(vector: Vector3d) {
    putDouble(vector.x)
    putDouble(vector.y)
    putDouble(vector.z)
}
fun ByteBuffer.putVector(vector: Vector4d) {
    putDouble(vector.x)
    putDouble(vector.y)
    putDouble(vector.z)
    putDouble(vector.w)
}
fun ByteBuffer.putVector(index: Int, vector: Vector2d) {
    putDouble(index+0, vector.x)
    putDouble(index+8, vector.y)
}
fun ByteBuffer.putVector(index: Int, vector: Vector3d) {
    putDouble(index+0, vector.x)
    putDouble(index+8, vector.y)
    putDouble(index+16, vector.z)
}
fun ByteBuffer.putVector(index: Int, vector: Vector4d) {
    putDouble(index+0, vector.x)
    putDouble(index+8, vector.y)
    putDouble(index+16, vector.z)
    putDouble(index+24, vector.w)
}

fun ByteBuffer.getVector2i(index: Int) =
        Vector2i(getInt(index), getInt(index+4))
fun ByteBuffer.getVector3i(index: Int) =
        Vector3i(getInt(index), getInt(index+4), getInt(index+8))
fun ByteBuffer.getVector4i(index: Int) =
        Vector4i(getInt(index), getInt(index+4), getInt(index+8), getInt(index+12))

fun ByteBuffer.getVector2iAsShort(index: Int) =
        Vector2i(getShort(index).toInt(), getShort(index+2).toInt())
fun ByteBuffer.getVector3iAsShort(index: Int) =
        Vector3i(getShort(index).toInt(), getShort(index+2).toInt(), getShort(index+4).toInt())
fun ByteBuffer.getVector4iAsShort(index: Int) =
        Vector4i(getShort(index).toInt(), getShort(index+2).toInt(), getShort(index+4).toInt(), getShort(index+6).toInt())

fun ByteBuffer.getVector2iAsUnsignedShort(index: Int) =
        Vector2i(getShort(index).toIntUnsigned(), getShort(index+2).toIntUnsigned())
fun ByteBuffer.getVector3iAsUnsignedShort(index: Int) =
        Vector3i(getShort(index).toIntUnsigned(), getShort(index+2).toIntUnsigned(), getShort(index+4).toIntUnsigned())
fun ByteBuffer.getVector4iAsUnsignedShort(index: Int) =
        Vector4i(getShort(index).toIntUnsigned(), getShort(index+2).toIntUnsigned(), getShort(index+4).toIntUnsigned(), getShort(index+6).toIntUnsigned())

fun ByteBuffer.getVector2iAsByte(index: Int) =
        Vector2i(get(index).toInt(), get(index+1).toInt())
fun ByteBuffer.getVector3iAsByte(index: Int) =
        Vector3i(get(index).toInt(), get(index+1).toInt(), get(index+2).toInt())
fun ByteBuffer.getVector4iAsByte(index: Int) =
        Vector4i(get(index).toInt(), get(index+1).toInt(), get(index+2).toInt(), get(index+3).toInt())

fun ByteBuffer.getVector2iAsUnsignedByte(index: Int) =
        Vector2i(get(index).toIntUnsigned(), get(index+1).toIntUnsigned())
fun ByteBuffer.getVector3iAsUnsignedByte(index: Int) =
        Vector3i(get(index).toIntUnsigned(), get(index+1).toIntUnsigned(), get(index+2).toIntUnsigned())
fun ByteBuffer.getVector4iAsUnsignedByte(index: Int) =
        Vector4i(get(index).toIntUnsigned(), get(index+1).toIntUnsigned(), get(index+2).toIntUnsigned(), get(index+3).toIntUnsigned())

fun ByteBuffer.getVector2f(index: Int) =
        Vector2f(getFloat(index), getFloat(index+4))
fun ByteBuffer.getVector3f(index: Int) =
        Vector3f(getFloat(index), getFloat(index+4), getFloat(index+8))
fun ByteBuffer.getVector4f(index: Int) =
        Vector4f(getFloat(index), getFloat(index+4), getFloat(index+8), getFloat(index+12))

fun ByteBuffer.getVector2d(index: Int) =
        Vector2d(getDouble(index), getDouble(index+8))
fun ByteBuffer.getVector3d(index: Int) =
        Vector3d(getDouble(index), getDouble(index+8), getDouble(index+16))
fun ByteBuffer.getVector4d(index: Int) =
        Vector4d(getDouble(index), getDouble(index+8), getDouble(index+16), getDouble(index+24))