package minuskelvin.gamelib.gl

import minuskelvin.gamelib.graphics.Color
import minuskelvin.gamelib.math.vector.Vector2i
import org.lwjgl.stb.STBIIOCallbacks
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load_from_callbacks
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.jemalloc.JEmalloc.je_free
import org.lwjgl.system.jemalloc.JEmalloc.je_malloc
import java.io.InputStream
import java.nio.ByteBuffer

sealed class Image constructor(internal val buffer: ByteBuffer, val size: Vector2i) : AutoCloseable {
    constructor(pair: Pair<ByteBuffer, Vector2i>): this(pair.first, pair.second)
    
    operator fun get(x: Int, y: Int): Color {
        if (x < 0 || x >= size.x)
            throw IndexOutOfBoundsException("$x")
        if (y < 0 || y >= size.y)
            throw IndexOutOfBoundsException("$y")
        return Color(buffer.getInt(x*4 + y*4*size.x))
    }
    
    operator fun set(x: Int, y: Int, color: Color) {
        if (x < 0 || x >= size.x)
            throw IndexOutOfBoundsException("$x")
        if (y < 0 || y >= size.y)
            throw IndexOutOfBoundsException("$y")
        buffer.putInt(x*4 + y*4*size.x, color.rgba8)
    }
}

class MemImage(size: Vector2i) : Image(je_malloc(4 * size.x.toLong() * size.y), size) {
    override fun close() {
        je_free(buffer)
    }
}

class STBImage(source: InputStream) : Image(readImage(source)) {
    override fun close() {
        stbi_image_free(buffer)
    }
}

private fun readImage(source: InputStream): Pair<ByteBuffer, Vector2i> {
    source.use {
        stackPush().use { stack ->
            var atEof = false
            val read = read@ { _: Long, data: Long, size: Int ->
                val buf = MemoryUtil.memByteBuffer(data, size)
                for (i in 0 until size) {
                    val c = source.read()
                    if (c == -1) {
                        atEof = true
                        return@read i
                    }
                    buf.put(c.toByte())
                }
                return@read size
            }
            val skip = { _: Long, size: Int ->
                for (i in 0 until size) {
                    val c = source.read()
                    if (c == -1)
                        atEof = true
                }
            }
            val eof = { _: Long ->
                if (atEof) 1 else 0
            }
            val callbacks = STBIIOCallbacks.mallocStack()
            callbacks.read(read)
            callbacks.skip(skip)
            callbacks.eof(eof)
            val x = stack.ints(0)
            val y = stack.ints(0)
            val image = stbi_load_from_callbacks(callbacks, 0L, x, y, stack.ints(0), 4)
            return Pair(image, Vector2i(x.get(0), y.get(0)))
        }
    }
}