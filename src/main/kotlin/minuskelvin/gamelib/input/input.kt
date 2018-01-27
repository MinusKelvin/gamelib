package minuskelvin.gamelib.input

import org.lwjgl.glfw.GLFW.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.reflect.KMutableProperty0

open class InputController internal constructor(window: Long) {
    private var locked = false
    
    internal val inputs = ArrayList<Input>()
    internal val bindings = HashMap<InputSource, MutableList<Binding>>()
    
    private val keycb = ::keyCB
    private val mousebuttoncb = ::mousebuttonCB
    private val winclosecb = ::wincloseCB
    private val scrollcb = ::scrollCB
    private val cursorcb = ::cursorCB
    
    private var mouse: MouseInput? = null
    
    init {
        glfwSetKeyCallback(window, keycb)
        glfwSetMouseButtonCallback(window, mousebuttoncb)
        glfwSetWindowCloseCallback(window, winclosecb)
        glfwSetScrollCallback(window, scrollcb)
    }
    
    fun createEventInput(): EventInput {
        if (locked) throw IllegalStateException("Inputs can only be created in the initializer")
        val input = EventInput(this)
        inputs += input
        return input
    }
    
    fun createButtonInput(): ButtonInput {
        if (locked) throw IllegalStateException("Inputs can only be created in the initializer")
        val input = ButtonInput(this)
        inputs += input
        return input
    }
    
    fun createAxisInput(): AxisInput {
        if (locked) throw IllegalStateException("Inputs can only be created in the initializer")
        val input = AxisInput(this)
        inputs += input
        return input
    }
    
    fun createMouseInput(): MouseInput {
        if (locked) throw IllegalStateException("Inputs can only be created in the initializer")
        if (mouse != null) return mouse!!
        mouse = MouseInput(this)
        inputs += mouse!!
        return mouse!!
    }
    
    internal fun lock() {
        locked = true
    }

    internal open fun preTick() {
        inputs.forEach { it.preTick() }
    }
    
    internal open fun postTick() {
        inputs.forEach { it.postTick() }
    }
    
    internal open fun close() {}
    
    private fun keyCB(_0: Long, key: Int, _1: Int, action: Int, _2: Int) {
        if (action == GLFW_REPEAT) return
        val source = keysToInputSource[key] ?: return
        bindings[source]?.forEach {
            it.process(source, when (action) {
                GLFW_PRESS -> 1f
                GLFW_RELEASE -> 0f
                else -> error("Invalid argument in GLFW callback")
            })
        }
    }
    
    private fun mousebuttonCB(_0: Long, button: Int, action: Int, _1: Int) {
        val source = mouseToInputSource[button] ?: return
        bindings[source]?.forEach {
            it.process(source, when (action) {
                GLFW_PRESS -> 1f
                GLFW_RELEASE -> 0f
                else -> error("Invalid argument in GLFW callback")
            })
        }
    }
    
    private fun wincloseCB(_0: Long) {
        bindings[EventSource.WINDOW_CLOSE]?.forEach {
            it.process(EventSource.WINDOW_CLOSE, 1f)
        }
    }
    
    private fun scrollCB(_0: Long, _1: Double, y: Double) {
        val source = when {
            y < 0.0 -> EventSource.SCROLL_DOWN
            y > 0.0 -> EventSource.SCROLL_UP
            else -> return
        }
        bindings[source]?.forEach {
            it.process(source, 1f)
        }
    }
    
    private fun cursorCB(_0: Long, x: Double, y: Double) {
        if (mouse != null) {
            mouse!!.x = x.toInt()
            mouse!!.y = y.toInt()
        }
    }
}

internal class RecordingInputController(window: Long, val dest: DataOutputStream) : InputController(window) {
    override fun preTick() {
        super.preTick()
        inputs.forEach {
            dest.writeInt(it.encodeState())
        }
    }

    override fun close() {
        dest.close()
    }
}

internal class PlaybackInputController(window: Long, val src: DataInputStream) : InputController(window) {
    override fun preTick() {
        super.preTick()
        inputs.forEach {
            it.decodeState(src.readInt())
        }
    }

    override fun close() {
        src.close()
    }
}

abstract class Input(internal val ic: InputController) {
    internal val _bindings = ArrayList<Binding>()
    
    val bindings: List<Binding> get() = _bindings
    
    internal abstract fun encodeState(): Int
    internal abstract fun decodeState(v: Int)
    internal open     fun preTick() {}
    internal open     fun postTick() {}
}

class EventInput internal constructor(ic: InputController) : Input(ic) {
    var state = false; private set

    override fun encodeState() = if (state) 1 else 0

    override fun decodeState(v: Int) {
        state = v != 0
    }

    override fun postTick() {
        state = false
    }

    fun bind(source: EventSource) =
            EventBinding(this, source, { state = true }, 0.5f)
    
    fun bind(source: ButtonSource, press: Boolean = true) =
            EventBinding(this, source, { state = true }, 0.5f, press)
    
    fun bind(source: AxisSource, threshold: Float = 0.5f) =
            EventBinding(this, source, { state = true }, threshold)
}

class ButtonInput internal constructor(ic: InputController) : Input(ic) {
    var state = false; private set

    override fun encodeState() = if (state) 1 else 0

    override fun decodeState(v: Int) {
        state = v != 0
    }
    
    fun bind(source: ButtonSource) =
            ButtonBinding(this, source, ::state, 0.5f)
    
    fun bind(source: AxisSource, threshold: Float = 0.5f) =
            ButtonBinding(this, source, ::state, threshold)
}

class AxisInput internal constructor(ic: InputController) : Input(ic) {
    var value = 0f; private set

    override fun encodeState() = value.toBits()

    override fun decodeState(v: Int) {
        value = Float.fromBits(v)
    }
    
    fun bind(source: AxisSource) =
            AxisBinding(this, source, ::value)
    
    fun bind(negative: ButtonSource, positive: ButtonSource) =
            AxisButtonBinding(this, negative, positive, ::value)
}

class MouseInput internal constructor(ic: InputController) : Input(ic) {
    var x = 0; internal set
    var y = 0; internal set
    
    override fun encodeState() = x or (y shl 16)

    override fun decodeState(v: Int) {
        x = v and 0xffff
        y = (v shr 16) and 0xffff
    }
}

sealed class Binding(private val input: Input, internal val sources: List<InputSource>) {
    constructor(input: Input, source: InputSource): this(input, Collections.singletonList(source))
    
    init {
        sources.forEach {
            input.ic.bindings.getOrPut(it, { ArrayList() }) += this
        }
        input._bindings.add(this)
    }
    
    open fun remove() {
        sources.forEach {
            val list = input.ic.bindings[it] ?: return
            list.remove(this)
            if (list.isEmpty())
                input.ic.bindings.remove(it)
        }
        input._bindings.remove(this)
    }

    /**
     * * Axis inputs: `value` is the value of the axis from `-1.0` to `1.0`
     * * Button inputs: `value` is `0.0` for release and `1.0` for held
     * * Event inputs: `value` is `1.0`
     */
    internal abstract fun process(source: InputSource, value: Float)
}

class EventBinding internal constructor(
        input: Input,
        val source: InputSource,
        private val event: () -> Unit,
        private val threshold: Float,
        val greater: Boolean = true
) : Binding(input, source) {
    override fun process(source: InputSource, value: Float) {
        if (greater && value > threshold || !greater && value < threshold)
            event()
    }
}

class ButtonBinding internal constructor(
        input: Input,
        val source: InputSource,
        private val property: KMutableProperty0<Boolean>,
        private val threshold: Float
) : Binding(input, source) {
    override fun process(source: InputSource, value: Float) {
        property.set(value * threshold.sign > threshold.absoluteValue)
    }
}

class AxisBinding internal constructor(
        input: Input,
        val source: InputSource,
        private val property: KMutableProperty0<Float>
) : Binding(input, source) {
    override fun process(source: InputSource, value: Float) {
        property.set(value)
    }
}

class AxisButtonBinding internal constructor(
        input: Input,
        val negativeSource: ButtonSource,
        val positiveSource: ButtonSource,
        private val property: KMutableProperty0<Float>
) : Binding(input, listOf(negativeSource, positiveSource)) {
    override fun process(source: InputSource, value: Float) {
        when (source) {
            negativeSource -> property.set(property.get() - (value * 2 - 1))
            positiveSource -> property.set(property.get() + (value * 2 - 1))
        }
    }
}