package minuskelvin.gamelib.input

import java.io.DataInputStream
import java.io.DataOutputStream
import kotlin.reflect.KProperty

sealed class Input(internal val ic: InputHandler, internal val _bindings: MutableSet<InputSource> = HashSet()) {
    val bindings: Set<InputSource> get() = _bindings
    
    internal abstract fun encodeState(dst: DataOutputStream)
    internal abstract fun decodeState(src: DataInputStream)

    protected open fun bind(binding: InputSource) {
        // Establish invariant that if x is in _bindings, ic.bindings[binding] contains a list that contains this
        if (_bindings.add(binding))
            ic.bindings.getOrPut(binding, { ArrayList() }).add(this)
    }

    protected open fun unbind(binding: InputSource) {
        if (_bindings.remove(binding)) {
            // Never throws NPE, as established by the invariant commented in bind()
            ic.bindings[binding]!!.remove(this)
            // Enforce invariant that ic.bindings does not contain empty lists
            if (ic.bindings[binding]!!.isEmpty())
                ic.bindings.remove(binding)
        }
    }
}

class ActionInput internal constructor(ic: InputHandler) : Input(ic) {
    var triggered = false
        internal set

    override fun encodeState(dst: DataOutputStream) {
        dst.writeBoolean(triggered)
    }

    override fun decodeState(src: DataInputStream) {
        triggered = src.readBoolean()
    }
    
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = triggered

    public override fun bind(binding: InputSource) = super.bind(binding)
    public override fun unbind(binding: InputSource) = super.unbind(binding)
}

class ButtonInput internal constructor(ic: InputHandler) : Input(ic) {
    var state = ButtonState.UNPRESSED
        internal set

    override fun encodeState(dst: DataOutputStream) {
        dst.writeByte(state.ordinal)
    }

    override fun decodeState(src: DataInputStream) {
        state = ButtonState.values()[src.readUnsignedByte()]
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = state

    fun bind(binding: ButtonOrAxisSource) = super.bind(binding)
    fun unbind(binding: ButtonOrAxisSource) = super.unbind(binding)
}

class AxisInput internal constructor(ic: InputHandler) : Input(ic) {
    var deadzone = 0.2f
    var value = 0f
        internal set(v) {
            field = if (v < deadzone) 0f else (v - deadzone) / (1 - deadzone)
        }

    override fun encodeState(dst: DataOutputStream) {
        dst.writeFloat(value)
    }

    override fun decodeState(src: DataInputStream) {
        value = src.readFloat()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

    fun bind(binding: ButtonOrAxisSource) = super.bind(binding)
    fun unbind(binding: ButtonOrAxisSource) = super.unbind(binding)
}

enum class ButtonState(val down: Boolean) {
    UNPRESSED(false), PRESS(true), HOLD(true), RELEASE(false);
}