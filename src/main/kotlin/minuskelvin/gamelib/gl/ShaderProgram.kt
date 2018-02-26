package minuskelvin.gamelib.gl

import org.lwjgl.opengl.GL11.GL_TRUE
import org.lwjgl.opengl.GL20.*

class ShaderProgram(vertexSource: String, fragmentSource: String) : AutoCloseable {
    val id: Int
    
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
    }
    
    fun use() {
        glUseProgram(id)
    }
    
    fun getUniformLocation(name: String) = glGetUniformLocation(id, name)

    override fun close() {
        glDeleteProgram(id)
    }
}