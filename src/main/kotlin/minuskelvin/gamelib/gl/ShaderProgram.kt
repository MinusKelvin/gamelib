package minuskelvin.gamelib.gl

import minuskelvin.gamelib.graphics.Camera
import minuskelvin.gamelib.math.matrix.putMatrix
import org.lwjgl.opengl.GL11.GL_TRUE
import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack

class ShaderProgram(vertexSource: String, fragmentSource: String, cameraUniform: String) : AutoCloseable {
    val id: Int
    val cameraLocation: Int
    
    init {
        val vertex = glCreateShader(GL_VERTEX_SHADER)
        glShaderSource(vertex, vertexSource)
        glCompileShader(vertex)
        if (glGetShaderi(vertex, GL_COMPILE_STATUS) != GL_TRUE)
            error(glGetShaderInfoLog(vertex))
        
        val fragment = glCreateShader(GL_FRAGMENT_SHADER)
        glShaderSource(fragment, fragmentSource)
        glCompileShader(fragment)
        if (glGetShaderi(fragment, GL_COMPILE_STATUS) != GL_TRUE)
            error(glGetShaderInfoLog(fragment))
        
        id = glCreateProgram()
        glAttachShader(id, vertex)
        glAttachShader(id, fragment)
        glLinkProgram(id)
        if (glGetProgrami(id, GL_LINK_STATUS) != GL_TRUE)
            error(glGetProgramInfoLog(id))
        
        glDeleteShader(vertex)
        glDeleteShader(fragment)
        
        cameraLocation = getUniformLocation(cameraUniform)
    }
    
    fun use(camera: Camera) {
        glUseProgram(id)
        MemoryStack.stackPush().use { stack ->
            val buf = stack.mallocFloat(16)
            buf.putMatrix(0, camera.matrix)
            glUniformMatrix4fv(cameraLocation, false, buf)
        }
    }
    
    fun getUniformLocation(name: String) = glGetUniformLocation(id, name)

    override fun close() {
        glDeleteProgram(id)
    }
}