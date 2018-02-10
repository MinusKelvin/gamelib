#version 330 core

layout(location = 0) in vec3 pos;
layout(location = 1) in vec4 color;

out vec4 c;

void main() {
	gl_Position = vec4(pos, 1.0);
	c = color;
}
