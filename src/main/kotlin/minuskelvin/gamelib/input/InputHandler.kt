package minuskelvin.gamelib.input

import minuskelvin.gamelib.Application
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWGamepadState
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.system.MemoryStack.stackPush
import java.util.*
import kotlin.math.max

class InputHandler internal constructor(private val app: Application, window: Long) {
    
    private val controllers = Array<Controller?>(GLFW_JOYSTICK_LAST + 1, { null })
    
    private val _inputs = ArrayList<Input>()
    val inputs: List<Input> get() = _inputs
    
    /** Invariant: if `bindings[x]` exists, `bindings[x]` is not null and has nonzero length */
    internal val bindings = HashMap<InputSource, MutableList<Input>>()
    
    internal fun pollInput() {
        glfwPollEvents()
        controllers.filterNotNull().forEach { it.poll() }
    }

    /**
     * Clear the triggered state for `ActionInput`s and transitions the state of `ButtonInput`s
     * from the `PRESS` and `RELEASE` states into the `HOLD` and `UNPRESSED` states.
     */
    fun tick() {
        inputs.forEach {
            when (it) {
                is ActionInput -> it.triggered = false
                is ButtonInput -> when (it.state) {
                    ButtonState.PRESS -> it.state = ButtonState.HOLD
                    ButtonState.RELEASE -> it.state = ButtonState.UNPRESSED
                    else -> Unit
                }
            }
        }
    }
    
    fun createActionInput(): ActionInput {
        val input = ActionInput(this)
        _inputs += input
        return input
    }
    
    fun createButtonInput(): ButtonInput {
        val input = ButtonInput(this)
        _inputs += input
        return input
    }
    
    fun createAxisInput(): AxisInput {
        val input = AxisInput(this)
        _inputs += input
        return input
    }
    
    fun getBoundInputs(source: InputSource): List<Input> = bindings[source] ?: Collections.emptyList()
    
    private var askForBinding: BindingAskState? = null
    
    private fun actionEvent(source: ButtonOrAxisSource) {
        askForBinding?.let {
            when (it) {
                is ActionAsk -> it.callback(source)
                is ButtonOrAxisAsk -> it.callback(source)
            }
            askForBinding = null
        } ?: bindings[source]?.forEach {
            if (it is ActionInput)
                it.triggered = true
        }
    }
    
    private fun actionEvent(source: ActionSource) {
        askForBinding?.let {
            when (it) {
                is ActionAsk -> it.callback(source)
                is ButtonOrAxisAsk -> return
            }
            askForBinding = null
        } ?: bindings[source]?.forEach {
            if (it is ActionInput)
                it.triggered = true
        }
    }
    
    private fun buttonOrAxisEvent(source: ButtonOrAxisSource, value: Float) {
        bindings[source]?.forEach {
            when (it) {
                is ButtonInput -> it.state = if (value >= 0.5) {
                    when (it.state) {
                        ButtonState.HOLD -> ButtonState.HOLD
                        else -> ButtonState.PRESS
                    }
                } else {
                    when (it.state) {
                        ButtonState.UNPRESSED -> ButtonState.UNPRESSED
                        else -> ButtonState.RELEASE
                    }
                }
                is AxisInput -> it.value = value
            }
        }
    }

    private val keyCB = f@{ _: Long, key: Int, _: Int, action: Int, _: Int ->
        val source = keyToSource[key] ?: return@f
        buttonOrAxisEvent(source, when (action) {
            GLFW_PRESS -> { actionEvent(source); 1f }
            GLFW_RELEASE -> 0f
            else -> return@f
        })
    }

    private val mouseButtonCB = f@{ _: Long, button: Int, action: Int, _: Int ->
        val source = mouseButtonToSource[button] ?: return@f
        buttonOrAxisEvent(source, when (action) {
            GLFW_PRESS -> { actionEvent(source); 1f }
            GLFW_RELEASE -> 0f
            else -> return@f
        })
    }

    private val mousePositionCB = f@{ _: Long, x: Double, y: Double ->
//        mouseInput?.x = x
//        mouseInput?.y = y
    }

    private val scrollCB = f@{ _: Long, _: Double, y: Double ->
        actionEvent(when {
            y > 0.0 -> ActionSource.SCROLL_UP
            y < 0.0 -> ActionSource.SCROLL_DOWN
            else -> return@f
        })
    }

    private val closeCB = { _: Long ->
        app.state?.windowClose()
        Unit
    }
    
    private val fbosizeCB = { _: Long, w: Int, h: Int ->
        glViewport(0, 0, w, h)
        app.state?.windowResize(w, h)
        Unit
    }

    private val joystickCB = { id: Int, event: Int ->
        controllers[id] = when (event) {
            GLFW_CONNECTED -> Controller(id)
            GLFW_DISCONNECTED -> null
            else -> throw AssertionError("Illegal argument in GLFW callback")
        }
        Unit
    }

    init {
        glfwSetKeyCallback(window, keyCB)
        glfwSetMouseButtonCallback(window, mouseButtonCB)
        glfwSetCursorPosCallback(window, mousePositionCB)
        glfwSetScrollCallback(window, scrollCB)
        glfwSetWindowCloseCallback(window, closeCB)
        glfwSetFramebufferSizeCallback(window, fbosizeCB)
        glfwSetJoystickCallback(joystickCB)

        for (i in 0 until controllers.size) {
            if (glfwJoystickIsGamepad(i))
                controllers[i] = Controller(i)
        }
    }

    private inner class Controller(val id: Int) {
        var a      = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_A);      field = value }
        var b      = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_B);      field = value }
        var x      = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_X);      field = value }
        var y      = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_Y);      field = value }
        var lb     = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LB);     field = value }
        var rb     = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RB);     field = value }
        var ls     = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LS);     field = value }
        var rs     = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RS);     field = value }
        var start  = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_START);  field = value }
        var select = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_SELECT); field = value }
        var up     = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_UP);     field = value }
        var down   = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_DOWN);   field = value }
        var left   = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LEFT);   field = value }
        var right  = false; set(value) { maybeButtonTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RIGHT);  field = value }

        var ls_px = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LS_X_PLUS);  field = value }
        var ls_nx = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LS_X_MINUS); field = value }
        var ls_py = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LS_Y_PLUS);  field = value }
        var ls_ny = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LS_Y_MINUS); field = value }
        var rs_px = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RS_X_PLUS);  field = value }
        var rs_nx = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RS_X_MINUS); field = value }
        var rs_py = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RS_Y_PLUS);  field = value }
        var rs_ny = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RS_Y_MINUS); field = value }
        var lt    = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_LEFT_TRIGGER); field = value }
        var rt    = 0f; set(value) { maybeAxisTrigger(field, value, ButtonOrAxisSource.CONTROLLER_RIGHT_TRIGGER); field = value }

        fun poll() {
            stackPush().use {
                val gamepad = GLFWGamepadState.mallocStack()!!
                glfwGetGamepadState(id, gamepad)

                a = gamepad.buttons(GLFW_GAMEPAD_BUTTON_A).toInt() == GLFW_PRESS
                b = gamepad.buttons(GLFW_GAMEPAD_BUTTON_B).toInt() == GLFW_PRESS
                x = gamepad.buttons(GLFW_GAMEPAD_BUTTON_X).toInt() == GLFW_PRESS
                y = gamepad.buttons(GLFW_GAMEPAD_BUTTON_Y).toInt() == GLFW_PRESS
                lb = gamepad.buttons(GLFW_GAMEPAD_BUTTON_LEFT_BUMPER).toInt() == GLFW_PRESS
                rb = gamepad.buttons(GLFW_GAMEPAD_BUTTON_RIGHT_BUMPER).toInt() == GLFW_PRESS
                ls = gamepad.buttons(GLFW_GAMEPAD_BUTTON_LEFT_THUMB).toInt() == GLFW_PRESS
                rs = gamepad.buttons(GLFW_GAMEPAD_BUTTON_RIGHT_THUMB).toInt() == GLFW_PRESS
                start = gamepad.buttons(GLFW_GAMEPAD_BUTTON_START).toInt() == GLFW_PRESS
                select = gamepad.buttons(GLFW_GAMEPAD_BUTTON_BACK).toInt() == GLFW_PRESS
                up = gamepad.buttons(GLFW_GAMEPAD_BUTTON_DPAD_UP).toInt() == GLFW_PRESS
                down = gamepad.buttons(GLFW_GAMEPAD_BUTTON_DPAD_DOWN).toInt() == GLFW_PRESS
                left = gamepad.buttons(GLFW_GAMEPAD_BUTTON_DPAD_LEFT).toInt() == GLFW_PRESS
                right = gamepad.buttons(GLFW_GAMEPAD_BUTTON_DPAD_RIGHT).toInt() == GLFW_PRESS

                ls_px = max(0f, gamepad.axes(GLFW_GAMEPAD_AXIS_LEFT_X))
                ls_nx = max(0f, -gamepad.axes(GLFW_GAMEPAD_AXIS_LEFT_X))
                ls_py = max(0f, gamepad.axes(GLFW_GAMEPAD_AXIS_LEFT_Y))
                ls_ny = max(0f, -gamepad.axes(GLFW_GAMEPAD_AXIS_LEFT_Y))
                rs_px = max(0f, gamepad.axes(GLFW_GAMEPAD_AXIS_RIGHT_X))
                rs_nx = max(0f, -gamepad.axes(GLFW_GAMEPAD_AXIS_RIGHT_X))
                rs_py = max(0f, gamepad.axes(GLFW_GAMEPAD_AXIS_RIGHT_Y))
                rs_ny = max(0f, -gamepad.axes(GLFW_GAMEPAD_AXIS_RIGHT_Y))

                lt = gamepad.axes(GLFW_GAMEPAD_AXIS_LEFT_TRIGGER) / 2 + 1
                rt = gamepad.axes(GLFW_GAMEPAD_AXIS_RIGHT_TRIGGER) / 2 + 1
            }
        }

        private fun maybeButtonTrigger(old: Boolean, new: Boolean, source: ButtonOrAxisSource) {
            if (old != new) {
                buttonOrAxisEvent(source, if (new) 1f else 0f)
                actionEvent(source)
            }
        }

        private fun maybeAxisTrigger(old: Float, new: Float, source: ButtonOrAxisSource) {
            if (old != new)
                buttonOrAxisEvent(source, new)
            if (old < 0.5 && new >= 0.5)
                actionEvent(source)
        }
    }
}

private sealed class BindingAskState
private class ActionAsk(val callback: (InputSource) -> Unit) : BindingAskState()
private class ButtonOrAxisAsk(val callback: (ButtonOrAxisSource) -> Unit) : BindingAskState()