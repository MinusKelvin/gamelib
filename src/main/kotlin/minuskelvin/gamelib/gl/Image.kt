package minuskelvin.gamelib.gl

import minuskelvin.gamelib.graphics.Color
import org.lwjgl.stb.STBIIOCallbacks
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load_from_callbacks
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.jemalloc.JEmalloc.je_free
import org.lwjgl.system.jemalloc.JEmalloc.je_malloc
import java.io.InputStream
import java.nio.ByteBuffer

sealed class Image constructor(internal val buffer: ByteBuffer, val width: Int, val height: Int) : AutoCloseable {
    
    operator fun get(x: Int, y: Int): Color {
        if (x < 0 || x >= width)
            throw IndexOutOfBoundsException("$x")
        if (y < 0 || y >= height)
            throw IndexOutOfBoundsException("$y")
        return Color(buffer.getInt(x*4 + y*4*width))
    }
    
    operator fun set(x: Int, y: Int, color: Color) {
        if (x < 0 || x >= width)
            throw IndexOutOfBoundsException("$x")
        if (y < 0 || y >= height)
            throw IndexOutOfBoundsException("$y")
        buffer.putInt(x*4 + y*4*width, color.rgba)
    }
}

private class MemImage(width: Int, height: Int) : Image(je_malloc(4 * width.toLong() * height), width, height) {
    override fun close() {
        je_free(buffer)
    }
}

private class STBImage(buffer: ByteBuffer, width: Int, height: Int) : Image(buffer, width, height) {
    override fun close() {
        stbi_image_free(buffer)
    }
}

fun readImage(source: InputStream): Image {
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
            
            return STBImage(image, x[0], y[0])
        }
    }
}

fun createImage(w: Int, h: Int): Image = MemImage(w, h)