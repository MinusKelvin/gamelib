package minuskelvin.gamelib.input

sealed class InputSource

/**
 * Inputs that have a press and release event, and a state value.
 */
class ButtonSource private constructor(private val nameGetter: () -> String): InputSource() {
    companion object {
        val KEY_ESCAPE         = ButtonSource("Escape")
        val KEY_F1             = ButtonSource("F1")
        val KEY_F2             = ButtonSource("F2")
        val KEY_F3             = ButtonSource("F3")
        val KEY_F4             = ButtonSource("F4")
        val KEY_F5             = ButtonSource("F5")
        val KEY_F6             = ButtonSource("F6")
        val KEY_F7             = ButtonSource("F7")
        val KEY_F8             = ButtonSource("F8")
        val KEY_F9             = ButtonSource("F9")
        val KEY_F10            = ButtonSource("F10")
        val KEY_F11            = ButtonSource("F11")
        val KEY_F12            = ButtonSource("F12")
        val KEY_F13            = ButtonSource("F13")
        val KEY_F14            = ButtonSource("F14")
        val KEY_F15            = ButtonSource("F15")
        val KEY_F16            = ButtonSource("F16")
        val KEY_F17            = ButtonSource("F17")
        val KEY_F18            = ButtonSource("F18")
        val KEY_F19            = ButtonSource("F19")
        val KEY_F20            = ButtonSource("F20")
        val KEY_F21            = ButtonSource("F21")
        val KEY_F22            = ButtonSource("F22")
        val KEY_F23            = ButtonSource("F23")
        val KEY_F24            = ButtonSource("F24")
        val KEY_F25            = ButtonSource("F25")
        val KEY_GRAVE          = ButtonSource("`")
        val KEY_1              = ButtonSource("1")
        val KEY_2              = ButtonSource("2")
        val KEY_3              = ButtonSource("3")
        val KEY_4              = ButtonSource("4")
        val KEY_5              = ButtonSource("5")
        val KEY_6              = ButtonSource("6")
        val KEY_7              = ButtonSource("7")
        val KEY_8              = ButtonSource("8")
        val KEY_9              = ButtonSource("9")
        val KEY_0              = ButtonSource("0")
        val KEY_MINUS          = ButtonSource("-")
        val KEY_EQUALS         = ButtonSource("=")
        val KEY_BACKSPACE      = ButtonSource("Backspace")
        val KEY_TAB            = ButtonSource("Tab")
        val KEY_Q              = ButtonSource("Q")
        val KEY_W              = ButtonSource("W")
        val KEY_E              = ButtonSource("E")
        val KEY_R              = ButtonSource("R")
        val KEY_T              = ButtonSource("T")
        val KEY_Y              = ButtonSource("Y")
        val KEY_U              = ButtonSource("U")
        val KEY_I              = ButtonSource("I")
        val KEY_O              = ButtonSource("O")
        val KEY_P              = ButtonSource("P")
        val KEY_LEFT_BRACKET   = ButtonSource("[")
        val KEY_RIGHT_BRACKET  = ButtonSource("]")
        val KEY_BACKSLASH      = ButtonSource("\\")
        val KEY_CAPS_LOCK      = ButtonSource("Caps Lock")
        val KEY_A              = ButtonSource("A")
        val KEY_S              = ButtonSource("S")
        val KEY_D              = ButtonSource("D")
        val KEY_F              = ButtonSource("F")
        val KEY_G              = ButtonSource("G")
        val KEY_H              = ButtonSource("H")
        val KEY_J              = ButtonSource("J")
        val KEY_K              = ButtonSource("K")
        val KEY_L              = ButtonSource("L")
        val KEY_SEMICOLON      = ButtonSource(";")
        val KEY_APOSTROPHE     = ButtonSource("'")
        val KEY_ENTER          = ButtonSource("Enter")
        val KEY_LEFT_SHIFT     = ButtonSource("Left Shift")
        val KEY_Z              = ButtonSource("Z")
        val KEY_X              = ButtonSource("X")
        val KEY_C              = ButtonSource("C")
        val KEY_V              = ButtonSource("V")
        val KEY_B              = ButtonSource("B")
        val KEY_N              = ButtonSource("N")
        val KEY_M              = ButtonSource("M")
        val KEY_COMMA          = ButtonSource(",")
        val KEY_PERIOD         = ButtonSource(".")
        val KEY_SLASH          = ButtonSource("/")
        val KEY_RIGHT_SHIFT    = ButtonSource("Right Shift")
        val KEY_LEFT_CONTROL   = ButtonSource("Left Control")
        val KEY_LEFT_SUPER     = ButtonSource("Left Super")
        val KEY_LEFT_ALT       = ButtonSource("Left Alt")
        val KEY_SPACE          = ButtonSource("Spacebar")
        val KEY_RIGHT_ALT      = ButtonSource("Right Alt")
        val KEY_RIGHT_SUPER    = ButtonSource("Right Super")
        val KEY_MENU           = ButtonSource("Menu")
        val KEY_RIGHT_CONTROL  = ButtonSource("Right Control")
        val KEY_LEFT           = ButtonSource("Left")
        val KEY_RIGHT          = ButtonSource("Right")
        val KEY_UP             = ButtonSource("Up")
        val KEY_DOWN           = ButtonSource("Down")
        val KEY_INSERT         = ButtonSource("Insert")
        val KEY_DELETE         = ButtonSource("Delete")
        val KEY_HOME           = ButtonSource("Home")
        val KEY_END            = ButtonSource("End")
        val KEY_PAGE_UP        = ButtonSource("Page Up")
        val KEY_PAGE_DOWN      = ButtonSource("Page Down")
        val KEY_NUM_LOCK       = ButtonSource("Num lock")
        val KEY_NUM_SLASH      = ButtonSource("Numpad /")
        val KEY_NUM_STAR       = ButtonSource("Numpad *")
        val KEY_NUM_MINUS      = ButtonSource("Numpad -")
        val KEY_NUM_PLUS       = ButtonSource("Numpad +")
        val KEY_NUM_ENTER      = ButtonSource("Numpad Enter")
        val KEY_NUM_0          = ButtonSource("Numpad 0")
        val KEY_NUM_1          = ButtonSource("Numpad 1")
        val KEY_NUM_2          = ButtonSource("Numpad 2")
        val KEY_NUM_3          = ButtonSource("Numpad 3")
        val KEY_NUM_4          = ButtonSource("Numpad 4")
        val KEY_NUM_5          = ButtonSource("Numpad 5")
        val KEY_NUM_6          = ButtonSource("Numpad 6")
        val KEY_NUM_7          = ButtonSource("Numpad 7")
        val KEY_NUM_8          = ButtonSource("Numpad 8")
        val KEY_NUM_9          = ButtonSource("Numpad 8")
        val KEY_NUM_PERIOD     = ButtonSource("Numpad .")
        
        val MOUSE_1            = ButtonSource("Mouse 1")
        val MOUSE_2            = ButtonSource("Mouse 2")
        val MOUSE_3            = ButtonSource("Mouse 3")
        val MOUSE_4            = ButtonSource("Mouse 4")
        val MOUSE_5            = ButtonSource("Mouse 5")
        val MOUSE_6            = ButtonSource("Mouse 6")
        val MOUSE_7            = ButtonSource("Mouse 7")
        val MOUSE_8            = ButtonSource("Mouse 8")
        
        val GAMEPAD_A          = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_B          = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_X          = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_Y          = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_START      = ButtonSource("Start")
        val GAMEPAD_SELECT     = ButtonSource("Select")
        val GAMEPAD_RB         = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_LB         = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_LS         = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_RS         = ButtonSource { TODO("not yet implemented") }
        val GAMEPAD_DPAD_UP    = ButtonSource("DPad Up")
        val GAMEPAD_DPAD_DOWN  = ButtonSource("DPad Down")
        val GAMEPAD_DPAD_LEFT  = ButtonSource("DPad Left")
        val GAMEPAD_DPAD_RIGHT = ButtonSource("DPad Right")
    }

    private constructor(name: String): this({ name })

    val name get() = nameGetter()
}

/**
 * Inputs that have only only event associated with them.
 */
class EventSource private constructor(private val nameGetter: () -> String): InputSource() {
    companion object {
        val SCROLL_UP    = EventSource("Scroll Up")
        val SCROLL_DOWN  = EventSource("Scroll Down")
        val WINDOW_CLOSE = EventSource("Window Close")
    }

    private constructor(name: String): this({ name })

    val name get() = nameGetter()
}

/**
 * Inputs that have a value between `-1.0` and `1.0`, with `0.0` as the resting value.
 */
class AxisSource private constructor(private val nameGetter: () -> String): InputSource() {
    companion object {
        val GAMEPAD_LS_X     = AxisSource("LS X")
        val GAMEPAD_LS_Y     = AxisSource("LS Y")
        val GAMEPAD_RS_X     = AxisSource("RS X")
        val GAMEPAD_RS_Y     = AxisSource("RS Y")
        val GAMEPAD_RTRIGGER = AxisSource { TODO("not yet implemented") }
        val GAMEPAD_LTRIGGER = AxisSource { TODO("not yet implemented") }
    }

    private constructor(name: String): this({ name })

    val name get() = nameGetter()
}