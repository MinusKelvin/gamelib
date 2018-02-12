package minuskelvin.gamelib.gl

import minuskelvin.gamelib.math.vector.Vector2f
import minuskelvin.gamelib.math.vector.Vector3f
import minuskelvin.gamelib.math.vector.Vector4f
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL12.GL_UNSIGNED_INT_2_10_10_10_REV
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.glMapBufferRange
import org.lwjgl.opengl.GL30.glVertexAttribIPointer
import org.lwjgl.system.jemalloc.JEmalloc.je_free
import org.lwjgl.system.jemalloc.JEmalloc.je_malloc
import java.nio.ByteBuffer
import kotlin.reflect.KProperty

class VertexBuffer<T : VertexStruct<T>>(private val struct: T) : AutoCloseable {
    private val id = glGenBuffers()
    
    fun bind() = glBindBuffer(GL_ARRAY_BUFFER, id)
    
    private var mapped: ByteBuffer? = null
    private var length: Int? = null
    
    fun allocate(count: Int, usage: Int) {
        bind()
        length = count
        glBufferData(GL_ARRAY_BUFFER, count.toLong() * struct.stride, usage)
    }
    
    fun allocate(data: VertexArray<T>, usage: Int) {
        bind()
        length = data.length
        data.buffer.position(0)
        glBufferData(GL_ARRAY_BUFFER, data.buffer, usage)
    }
    
    fun update(data: VertexArray<T>, start: Int) {
        if (length == null)
            error("VertexBuffer has not been allocated")
        if (data.length + start > length!!)
            throw IllegalArgumentException("data overflows buffer")
        bind()
        data.buffer.position(0)
        glBufferSubData(GL_ARRAY_BUFFER, start.toLong() * struct.stride, data.buffer)
    }
    
    fun map(invalidate: Boolean = false): Mapped {
        if (length == null)
            error("VertexBuffer has not been allocated")
        bind()
        mapped = glMapBufferRange(GL_ARRAY_BUFFER, 0, length!!.toLong() * struct.stride, GL_WRITE_ONLY, mapped)
        return Mapped(mapped!!)
    }
    
    fun bindVertexLayout(): AutoCloseable {
        bind()
        struct.attributes.forEach { it.enableAndBind() }
        return AutoCloseable { struct.attributes.forEach { it.disable() } }
    }

    override fun close() {
        glDeleteBuffers(id)
    }
    
    inner class Mapped internal constructor(private val buffer: ByteBuffer) : AutoCloseable {
        operator fun get(index: Int): T {
            buffer.position(index * struct.stride)
            struct.ptr = buffer
            return struct
        }

        override fun close() {
            bind()
            glUnmapBuffer(GL_ARRAY_BUFFER)
        }
    }
}

class VertexArray<T: VertexStruct<T>>(private val struct: T, val length: Int) : AutoCloseable {
    internal var buffer = je_malloc(length.toLong() * struct.stride)

    operator fun get(index: Int): T {
        buffer.position(index * struct.stride)
        struct.ptr = buffer
        return struct
    }
    
    override fun close() {
        buffer.position(0)
        je_free(buffer)
    }
}

open class VertexStruct<T: VertexStruct<T>> {
    internal val attributes = ArrayList<Attribute>()
    internal var ptr: ByteBuffer? = null
    var stride = 0
        private set
    
    private fun fieldOffset(fieldSize: Int): Int {
        val tmp = stride
        stride += fieldSize
        stride += (4 - stride % 4) % 4
        return stride - fieldSize
    }

    abstract inner class Attribute(
            private val index: Int,
            private val glType: Int,
            private val size: Int,
            private val normalized: Boolean,
            protected val offset: Int,
            private val intvariant: Boolean = false
    ) {
        init {
            attributes += this
        }
        
        fun enableAndBind() {
            if (intvariant)
                glVertexAttribIPointer(index, size, glType, stride, offset.toLong())
            else
                glVertexAttribPointer(index, size, glType, normalized, stride, offset.toLong())
            glEnableVertexAttribArray(index)
        }
        
        fun disable() {
            glDisableVertexAttribArray(index)
        }
    }
    
    protected inner class FloatAttribute(index: Int) : Attribute(index, GL_FLOAT, 1, false, fieldOffset(4)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Float) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putFloat(ptr.position() + offset, value)
        }
        
        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Float {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return ptr.getFloat(ptr.position() + offset)
        }
    }

    protected inner class Vector2fAttribute(index: Int) : Attribute(index, GL_FLOAT, 2, false, fieldOffset(8)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector2f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putFloat(ptr.position() + offset + 0, value.x)
            ptr.putFloat(ptr.position() + offset + 4, value.y)
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector2f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector2f(
                    ptr.getFloat(ptr.position() + offset + 0),
                    ptr.getFloat(ptr.position() + offset + 4)
            )
        }
    }

    protected inner class Vector3fAttribute(index: Int) : Attribute(index, GL_FLOAT, 3, false, fieldOffset(12)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector3f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putFloat(ptr.position() + offset + 0, value.x)
            ptr.putFloat(ptr.position() + offset + 4, value.y)
            ptr.putFloat(ptr.position() + offset + 8, value.z)
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector3f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector3f(
                    ptr.getFloat(ptr.position() + offset + 0),
                    ptr.getFloat(ptr.position() + offset + 4),
                    ptr.getFloat(ptr.position() + offset + 8)
            )
        }
    }

    protected inner class Vector4fAttribute(index: Int) : Attribute(index, GL_FLOAT, 4, false, fieldOffset(16)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector4f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putFloat(ptr.position() + offset +  0, value.x)
            ptr.putFloat(ptr.position() + offset +  4, value.y)
            ptr.putFloat(ptr.position() + offset +  8, value.z)
            ptr.putFloat(ptr.position() + offset + 12, value.w)
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector4f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector4f(
                    ptr.getFloat(ptr.position() + offset + 0),
                    ptr.getFloat(ptr.position() + offset + 4),
                    ptr.getFloat(ptr.position() + offset + 8),
                    ptr.getFloat(ptr.position() + offset + 12)
            )
        }
    }

    protected inner class ShortVector2fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_SHORT, 2, true, fieldOffset(4)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector2f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putShort(ptr.position() + offset + 0, (value.x * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 2, (value.y * INT_16_MAX).toShort())
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector2f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector2f(
                    ptr.getShort(ptr.position() + offset + 0).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 2).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX
            )
        }
    }

    protected inner class ShortVector3fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_SHORT, 3, true, fieldOffset(6)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector3f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putShort(ptr.position() + offset + 0, (value.x * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 2, (value.y * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 4, (value.z * INT_16_MAX).toShort())
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector3f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector3f(
                    ptr.getShort(ptr.position() + offset + 0).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 2).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 4).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX
            )
        }
    }

    protected inner class ShortVector4fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_SHORT, 4, true, fieldOffset(8)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector4f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putShort(ptr.position() + offset + 0, (value.x * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 2, (value.y * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 4, (value.z * INT_16_MAX).toShort())
            ptr.putShort(ptr.position() + offset + 6, (value.w * INT_16_MAX).toShort())
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector4f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector4f(
                    ptr.getShort(ptr.position() + offset + 0).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 2).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 4).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX,
                    ptr.getShort(ptr.position() + offset + 6).toInt().and(INT_16_MAX).toFloat() / INT_16_MAX
            )
        }
    }

    protected inner class ByteVector3fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_BYTE, 3, true, fieldOffset(3)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector3f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.put(ptr.position() + offset + 0, (value.x * INT_8_MAX).toByte())
            ptr.put(ptr.position() + offset + 1, (value.y * INT_8_MAX).toByte())
            ptr.put(ptr.position() + offset + 2, (value.z * INT_8_MAX).toByte())
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector3f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector3f(
                    ptr.get(ptr.position() + offset + 0).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX,
                    ptr.get(ptr.position() + offset + 1).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX,
                    ptr.get(ptr.position() + offset + 2).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX
            )
        }
    }

    protected inner class ByteVector4fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_BYTE, 4, true, fieldOffset(4)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector4f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.put(ptr.position() + offset + 0, (value.x * INT_8_MAX).toByte())
            ptr.put(ptr.position() + offset + 1, (value.y * INT_8_MAX).toByte())
            ptr.put(ptr.position() + offset + 2, (value.z * INT_8_MAX).toByte())
            ptr.put(ptr.position() + offset + 3, (value.w * INT_8_MAX).toByte())
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector4f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            return Vector4f(
                    ptr.get(ptr.position() + offset + 0).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX,
                    ptr.get(ptr.position() + offset + 1).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX,
                    ptr.get(ptr.position() + offset + 2).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX,
                    ptr.get(ptr.position() + offset + 3).toInt().and(INT_8_MAX).toFloat() / INT_8_MAX
            )
        }
    }

    protected inner class Int10Vector4fAttribute(index: Int) : Attribute(index, GL_UNSIGNED_INT_2_10_10_10_REV, 4, true, fieldOffset(4)) {
        operator fun setValue(thisRef: VertexStruct<T>, property: KProperty<*>, value: Vector4f) {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            ptr.putInt(ptr.position() + offset,
                    ((value.x * INT_10_MAX).toInt() shl  0) and
                    ((value.y * INT_10_MAX).toInt() shl 10) and
                    ((value.z * INT_10_MAX).toInt() shl 20) and
                    ((value.w * INT_2_MAX) .toInt() shl 30))
        }

        operator fun getValue(thisRef: VertexStruct<T>, property: KProperty<*>): Vector4f {
            assert(thisRef === this@VertexStruct)
            val ptr = ptr!!
            val v = ptr.getInt(ptr.position() + offset)
            return Vector4f(
                    ((v shr 0) and INT_10_MAX).toFloat() / INT_10_MAX,
                    ((v shr 10) and INT_10_MAX).toFloat() / INT_10_MAX,
                    ((v shr 20) and INT_10_MAX).toFloat() / INT_10_MAX,
                    ((v shr 30) and INT_2_MAX).toFloat() / INT_2_MAX
            )
        }
    }
}

const val INT_2_MAX  = 0b11
const val INT_8_MAX  = 0b1111_1111
const val INT_10_MAX = 0b1111_1111_11
const val INT_16_MAX = 0b1111_1111_1111_1111
