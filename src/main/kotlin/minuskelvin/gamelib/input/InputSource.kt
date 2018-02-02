package minuskelvin.gamelib.input

import org.lwjgl.glfw.GLFW.*

sealed class InputSource

class ActionSource private constructor(val name: String, val ordinal: Int) : InputSource(), Comparable<ActionSource> {
    companion object {
        val SCROLL_UP = ActionSource("SCROLL_UP", 0)
        val SCROLL_DOWN = ActionSource("SCROLL_DOWN", 1)
        
        val values = arrayOf(SCROLL_UP, SCROLL_DOWN)
        
        fun valueOf(name: String) = values.find { it.name == name } ?: throw IllegalArgumentException("Invalid name: $name")
    }

    override fun toString() = name
    override fun compareTo(other: ActionSource) = ordinal.compareTo(other.ordinal)
}

class ButtonOrAxisSource private constructor(val name: String, val ordinal: Int) : InputSource(), Comparable<ButtonOrAxisSource> {
    companion object {
        val KEY_1 = ButtonOrAxisSource("KEY_1", 0)
        val KEY_2 = ButtonOrAxisSource("KEY_2", 1)
        val KEY_3 = ButtonOrAxisSource("KEY_3", 2)
        val KEY_4 = ButtonOrAxisSource("KEY_4", 3)
        val KEY_5 = ButtonOrAxisSource("KEY_5", 4)
        val KEY_6 = ButtonOrAxisSource("KEY_6", 5)
        val KEY_7 = ButtonOrAxisSource("KEY_7", 6)
        val KEY_8 = ButtonOrAxisSource("KEY_8", 7)
        val KEY_9 = ButtonOrAxisSource("KEY_9", 8)
        val KEY_0 = ButtonOrAxisSource("KEY_0", 9)
        val KEY_A = ButtonOrAxisSource("KEY_A", 10)
        val KEY_B = ButtonOrAxisSource("KEY_B", 11)
        val KEY_C = ButtonOrAxisSource("KEY_C", 12)
        val KEY_D = ButtonOrAxisSource("KEY_D", 13)
        val KEY_E = ButtonOrAxisSource("KEY_E", 14)
        val KEY_F = ButtonOrAxisSource("KEY_F", 15)
        val KEY_G = ButtonOrAxisSource("KEY_G", 16)
        val KEY_H = ButtonOrAxisSource("KEY_H", 17)
        val KEY_I = ButtonOrAxisSource("KEY_I", 18)
        val KEY_J = ButtonOrAxisSource("KEY_J", 19)
        val KEY_K = ButtonOrAxisSource("KEY_K", 20)
        val KEY_L = ButtonOrAxisSource("KEY_L", 21)
        val KEY_M = ButtonOrAxisSource("KEY_M", 22)
        val KEY_N = ButtonOrAxisSource("KEY_N", 23)
        val KEY_O = ButtonOrAxisSource("KEY_O", 24)
        val KEY_P = ButtonOrAxisSource("KEY_P", 25)
        val KEY_Q = ButtonOrAxisSource("KEY_Q", 26)
        val KEY_R = ButtonOrAxisSource("KEY_R", 27)
        val KEY_S = ButtonOrAxisSource("KEY_S", 28)
        val KEY_T = ButtonOrAxisSource("KEY_T", 29)
        val KEY_U = ButtonOrAxisSource("KEY_U", 30)
        val KEY_V = ButtonOrAxisSource("KEY_V", 31)
        val KEY_W = ButtonOrAxisSource("KEY_W", 32)
        val KEY_X = ButtonOrAxisSource("KEY_X", 33)
        val KEY_Y = ButtonOrAxisSource("KEY_Y", 34)
        val KEY_Z = ButtonOrAxisSource("KEY_Z", 35)
        val KEY_GRAVE = ButtonOrAxisSource("KEY_GRAVE", 36)
        val KEY_MINUS = ButtonOrAxisSource("KEY_MINUS", 37)
        val KEY_EQUAL = ButtonOrAxisSource("KEY_EQUAL", 38)
        val KEY_LEFT_BRACKET = ButtonOrAxisSource("KEY_LEFT_BRACKET", 39)
        val KEY_RIGHT_BRACKET = ButtonOrAxisSource("KEY_RIGHT_BRACKET", 40)
        val KEY_BACKSLASH = ButtonOrAxisSource("KEY_BACKSLASH", 41)
        val KEY_SEMICOLON = ButtonOrAxisSource("KEY_SEMICOLON", 42)
        val KEY_APOSTROPHE = ButtonOrAxisSource("KEY_APOSTROPHE", 43)
        val KEY_COMMA = ButtonOrAxisSource("KEY_COMMA", 44)
        val KEY_PERIOD = ButtonOrAxisSource("KEY_PERIOD", 45)
        val KEY_SLASH = ButtonOrAxisSource("KEY_SLASH", 46)
        val KEY_TAB = ButtonOrAxisSource("KEY_TAB", 47)
        val KEY_SPACE = ButtonOrAxisSource("KEY_SPACE", 48)
        val KEY_ESCAPE = ButtonOrAxisSource("KEY_ESCAPE", 49)
        val KEY_F1 = ButtonOrAxisSource("KEY_F1", 50)
        val KEY_F2 = ButtonOrAxisSource("KEY_F2", 51)
        val KEY_F3 = ButtonOrAxisSource("KEY_F3", 52)
        val KEY_F4 = ButtonOrAxisSource("KEY_F4", 53)
        val KEY_F5 = ButtonOrAxisSource("KEY_F5", 54)
        val KEY_F6 = ButtonOrAxisSource("KEY_F6", 55)
        val KEY_F7 = ButtonOrAxisSource("KEY_F7", 56)
        val KEY_F8 = ButtonOrAxisSource("KEY_F8", 57)
        val KEY_F9 = ButtonOrAxisSource("KEY_F9", 58)
        val KEY_F10 = ButtonOrAxisSource("KEY_F10", 59)
        val KEY_F11 = ButtonOrAxisSource("KEY_F11", 60)
        val KEY_F12 = ButtonOrAxisSource("KEY_F12", 61)
        val KEY_F13 = ButtonOrAxisSource("KEY_F13", 62)
        val KEY_F14 = ButtonOrAxisSource("KEY_F14", 63)
        val KEY_F15 = ButtonOrAxisSource("KEY_F15", 64)
        val KEY_F16 = ButtonOrAxisSource("KEY_F16", 65)
        val KEY_F17 = ButtonOrAxisSource("KEY_F17", 66)
        val KEY_F18 = ButtonOrAxisSource("KEY_F18", 67)
        val KEY_F19 = ButtonOrAxisSource("KEY_F19", 68)
        val KEY_F20 = ButtonOrAxisSource("KEY_F20", 69)
        val KEY_F21 = ButtonOrAxisSource("KEY_F21", 70)
        val KEY_F22 = ButtonOrAxisSource("KEY_F22", 71)
        val KEY_F23 = ButtonOrAxisSource("KEY_F23", 72)
        val KEY_F24 = ButtonOrAxisSource("KEY_F24", 73)
        val KEY_F25 = ButtonOrAxisSource("KEY_F25", 74)
        val KEY_BACKSPACE = ButtonOrAxisSource("KEY_BACKSPACE", 75)
        val KEY_CAPS_LOCK = ButtonOrAxisSource("KEY_CAPS_LOCK", 76)
        val KEY_ENTER = ButtonOrAxisSource("KEY_ENTER", 77)
        val KEY_LEFT_SHIFT = ButtonOrAxisSource("KEY_LEFT_SHIFT", 78)
        val KEY_RIGHT_SHIFT = ButtonOrAxisSource("KEY_RIGHT_SHIFT", 79)
        val KEY_LEFT_CONTROL = ButtonOrAxisSource("KEY_LEFT_CONTROL", 80)
        val KEY_RIGHT_CONTROL = ButtonOrAxisSource("KEY_RIGHT_CONTROL", 81)
        val KEY_LEFT_SUPER = ButtonOrAxisSource("KEY_LEFT_SUPER", 82)
        val KEY_RIGHT_SUPER = ButtonOrAxisSource("KEY_RIGHT_SUPER", 83)
        val KEY_LEFT_ALT = ButtonOrAxisSource("KEY_LEFT_ALT", 84)
        val KEY_RIGHT_ALT = ButtonOrAxisSource("KEY_RIGHT_ALT", 85)
        val KEY_MENU = ButtonOrAxisSource("KEY_MENU", 86)
        val KEY_INSERT = ButtonOrAxisSource("KEY_INSERT", 87)
        val KEY_DELETE = ButtonOrAxisSource("KEY_DELETE", 88)
        val KEY_HOME = ButtonOrAxisSource("KEY_HOME", 89)
        val KEY_END = ButtonOrAxisSource("KEY_END", 90)
        val KEY_PAGE_UP = ButtonOrAxisSource("KEY_PAGE_UP", 91)
        val KEY_PAGE_DOWN = ButtonOrAxisSource("KEY_PAGE_DOWN", 92)
        val KEY_UP = ButtonOrAxisSource("KEY_UP", 93)
        val KEY_DOWN = ButtonOrAxisSource("KEY_DOWN", 94)
        val KEY_LEFT = ButtonOrAxisSource("KEY_LEFT", 95)
        val KEY_RIGHT = ButtonOrAxisSource("KEY_RIGHT", 96)
        val KEY_NUM_LOCK = ButtonOrAxisSource("KEY_NUM_LOCK", 97)
        val KEY_NUMPAD_0 = ButtonOrAxisSource("KEY_NUMPAD_0", 98)
        val KEY_NUMPAD_1 = ButtonOrAxisSource("KEY_NUMPAD_1", 99)
        val KEY_NUMPAD_2 = ButtonOrAxisSource("KEY_NUMPAD_2", 100)
        val KEY_NUMPAD_3 = ButtonOrAxisSource("KEY_NUMPAD_3", 101)
        val KEY_NUMPAD_4 = ButtonOrAxisSource("KEY_NUMPAD_4", 102)
        val KEY_NUMPAD_5 = ButtonOrAxisSource("KEY_NUMPAD_5", 103)
        val KEY_NUMPAD_6 = ButtonOrAxisSource("KEY_NUMPAD_6", 104)
        val KEY_NUMPAD_7 = ButtonOrAxisSource("KEY_NUMPAD_7", 105)
        val KEY_NUMPAD_8 = ButtonOrAxisSource("KEY_NUMPAD_8", 106)
        val KEY_NUMPAD_9 = ButtonOrAxisSource("KEY_NUMPAD_9", 107)
        val KEY_NUMPAD_ADD = ButtonOrAxisSource("KEY_NUMPAD_ADD", 108)
        val KEY_NUMPAD_SUBTRACT = ButtonOrAxisSource("KEY_NUMPAD_SUBTRACT", 109)
        val KEY_NUMPAD_MULTIPLY = ButtonOrAxisSource("KEY_NUMPAD_MULTIPLY", 110)
        val KEY_NUMPAD_DIVIDE = ButtonOrAxisSource("KEY_NUMPAD_DIVIDE", 111)
        val KEY_NUMPAD_ENTER = ButtonOrAxisSource("KEY_NUMPAD_ENTER", 112)
        val KEY_NUMPAD_DECIMAL = ButtonOrAxisSource("KEY_NUMPAD_DECIMAL", 113)
        
        val MOUSE_BUTTON_1 = ButtonOrAxisSource("MOUSE_BUTTON_1", 114)
        val MOUSE_BUTTON_2 = ButtonOrAxisSource("MOUSE_BUTTON_2", 115)
        val MOUSE_BUTTON_3 = ButtonOrAxisSource("MOUSE_BUTTON_3", 116)
        val MOUSE_BUTTON_4 = ButtonOrAxisSource("MOUSE_BUTTON_4", 117)
        val MOUSE_BUTTON_5 = ButtonOrAxisSource("MOUSE_BUTTON_5", 118)
        val MOUSE_BUTTON_6 = ButtonOrAxisSource("MOUSE_BUTTON_6", 119)
        val MOUSE_BUTTON_7 = ButtonOrAxisSource("MOUSE_BUTTON_7", 120)
        val MOUSE_BUTTON_8 = ButtonOrAxisSource("MOUSE_BUTTON_8", 121)
        
        val CONTROLLER_A = ButtonOrAxisSource("CONTROLLER_A", 122)
        val CONTROLLER_B = ButtonOrAxisSource("CONTROLLER_B", 123)
        val CONTROLLER_X = ButtonOrAxisSource("CONTROLLER_X", 124)
        val CONTROLLER_Y = ButtonOrAxisSource("CONTROLLER_Y", 125)
        val CONTROLLER_LB = ButtonOrAxisSource("CONTROLLER_LB", 126)
        val CONTROLLER_RB = ButtonOrAxisSource("CONTROLLER_RB", 127)
        val CONTROLLER_LS = ButtonOrAxisSource("CONTROLLER_LS", 128)
        val CONTROLLER_RS = ButtonOrAxisSource("CONTROLLER_RS", 129)
        val CONTROLLER_START = ButtonOrAxisSource("CONTROLLER_START", 130)
        val CONTROLLER_SELECT = ButtonOrAxisSource("CONTROLLER_SELECT", 131)
        val CONTROLLER_UP = ButtonOrAxisSource("CONTROLLER_UP", 132)
        val CONTROLLER_DOWN = ButtonOrAxisSource("CONTROLLER_DOWN", 134)
        val CONTROLLER_LEFT = ButtonOrAxisSource("CONTROLLER_LEFT", 135)
        val CONTROLLER_RIGHT = ButtonOrAxisSource("CONTROLLER_RIGHT", 136)
        val CONTROLLER_LS_X_PLUS = ButtonOrAxisSource("CONTROLLER_LS_X_PLUS", 137)
        val CONTROLLER_LS_X_MINUS = ButtonOrAxisSource("CONTROLLER_LS_X_MINUS", 138)
        val CONTROLLER_LS_Y_PLUS = ButtonOrAxisSource("CONTROLLER_LS_Y_PLUS", 139)
        val CONTROLLER_LS_Y_MINUS = ButtonOrAxisSource("CONTROLLER_LS_Y_MINUS", 140)
        val CONTROLLER_RS_X_PLUS = ButtonOrAxisSource("CONTROLLER_RS_X_PLUS", 141)
        val CONTROLLER_RS_X_MINUS = ButtonOrAxisSource("CONTROLLER_RS_X_MINUS", 142)
        val CONTROLLER_RS_Y_PLUS = ButtonOrAxisSource("CONTROLLER_RS_Y_PLUS", 143)
        val CONTROLLER_RS_Y_MINUS = ButtonOrAxisSource("CONTROLLER_RS_Y_MINUS", 144)
        val CONTROLLER_RIGHT_TRIGGER = ButtonOrAxisSource("CONTROLLER_RIGHT_TRIGGER", 145)
        val CONTROLLER_LEFT_TRIGGER = ButtonOrAxisSource("CONTROLLER_LEFT_TRIGGER", 146)
        
        val values = arrayOf(
                KEY_1,
                KEY_2,
                KEY_3,
                KEY_4,
                KEY_5,
                KEY_6,
                KEY_7,
                KEY_8,
                KEY_9,
                KEY_0,
                KEY_A,
                KEY_B,
                KEY_C,
                KEY_D,
                KEY_E,
                KEY_F,
                KEY_G,
                KEY_H,
                KEY_I,
                KEY_J,
                KEY_K,
                KEY_L,
                KEY_M,
                KEY_N,
                KEY_O,
                KEY_P,
                KEY_Q,
                KEY_R,
                KEY_S,
                KEY_T,
                KEY_U,
                KEY_V,
                KEY_W,
                KEY_X,
                KEY_Y,
                KEY_Z,
                KEY_GRAVE,
                KEY_MINUS,
                KEY_EQUAL,
                KEY_LEFT_BRACKET,
                KEY_RIGHT_BRACKET,
                KEY_BACKSLASH,
                KEY_SEMICOLON,
                KEY_APOSTROPHE,
                KEY_COMMA,
                KEY_PERIOD,
                KEY_SLASH,
                KEY_TAB,
                KEY_SPACE,
                KEY_ESCAPE,
                KEY_F1,
                KEY_F2,
                KEY_F3,
                KEY_F4,
                KEY_F5,
                KEY_F6,
                KEY_F7,
                KEY_F8,
                KEY_F9,
                KEY_F10,
                KEY_F11,
                KEY_F12,
                KEY_F13,
                KEY_F14,
                KEY_F15,
                KEY_F16,
                KEY_F17,
                KEY_F18,
                KEY_F19,
                KEY_F20,
                KEY_F21,
                KEY_F22,
                KEY_F23,
                KEY_F24,
                KEY_F25,
                KEY_BACKSPACE,
                KEY_CAPS_LOCK,
                KEY_ENTER,
                KEY_LEFT_SHIFT,
                KEY_RIGHT_SHIFT,
                KEY_LEFT_CONTROL,
                KEY_RIGHT_CONTROL,
                KEY_LEFT_SUPER,
                KEY_RIGHT_SUPER,
                KEY_LEFT_ALT,
                KEY_RIGHT_ALT,
                KEY_MENU,
                KEY_INSERT,
                KEY_DELETE,
                KEY_HOME,
                KEY_END,
                KEY_PAGE_UP,
                KEY_PAGE_DOWN,
                KEY_UP,
                KEY_DOWN,
                KEY_LEFT,
                KEY_RIGHT,
                KEY_NUM_LOCK,
                KEY_NUMPAD_0,
                KEY_NUMPAD_1,
                KEY_NUMPAD_2,
                KEY_NUMPAD_3,
                KEY_NUMPAD_4,
                KEY_NUMPAD_5,
                KEY_NUMPAD_6,
                KEY_NUMPAD_7,
                KEY_NUMPAD_8,
                KEY_NUMPAD_9,
                KEY_NUMPAD_ADD,
                KEY_NUMPAD_SUBTRACT,
                KEY_NUMPAD_MULTIPLY,
                KEY_NUMPAD_DIVIDE,
                KEY_NUMPAD_ENTER,
                KEY_NUMPAD_DECIMAL,
                MOUSE_BUTTON_1,
                MOUSE_BUTTON_2,
                MOUSE_BUTTON_3,
                MOUSE_BUTTON_4,
                MOUSE_BUTTON_5,
                MOUSE_BUTTON_6,
                MOUSE_BUTTON_7,
                MOUSE_BUTTON_8,
                CONTROLLER_A,
                CONTROLLER_B,
                CONTROLLER_X,
                CONTROLLER_Y,
                CONTROLLER_LB,
                CONTROLLER_RB,
                CONTROLLER_LS,
                CONTROLLER_RS,
                CONTROLLER_START,
                CONTROLLER_SELECT,
                CONTROLLER_UP,
                CONTROLLER_DOWN,
                CONTROLLER_LEFT,
                CONTROLLER_RIGHT,
                CONTROLLER_LS_X_PLUS,
                CONTROLLER_LS_X_MINUS,
                CONTROLLER_LS_Y_PLUS,
                CONTROLLER_LS_Y_MINUS,
                CONTROLLER_RS_X_PLUS,
                CONTROLLER_RS_X_MINUS,
                CONTROLLER_RS_Y_PLUS,
                CONTROLLER_RS_Y_MINUS,
                CONTROLLER_RIGHT_TRIGGER,
                CONTROLLER_LEFT_TRIGGER
        )

        fun valueOf(name: String) = values.find { it.name == name } ?: throw IllegalArgumentException("Invalid name: $name")
    }

    override fun toString() = name
    override fun compareTo(other: ButtonOrAxisSource) = ordinal.compareTo(other.ordinal)
}

internal val keyToSource = mapOf(
        GLFW_KEY_1             to ButtonOrAxisSource.KEY_1,
        GLFW_KEY_2             to ButtonOrAxisSource.KEY_2,
        GLFW_KEY_3             to ButtonOrAxisSource.KEY_3,
        GLFW_KEY_4             to ButtonOrAxisSource.KEY_4,
        GLFW_KEY_5             to ButtonOrAxisSource.KEY_5,
        GLFW_KEY_6             to ButtonOrAxisSource.KEY_6,
        GLFW_KEY_7             to ButtonOrAxisSource.KEY_7,
        GLFW_KEY_8             to ButtonOrAxisSource.KEY_8,
        GLFW_KEY_9             to ButtonOrAxisSource.KEY_9,
        GLFW_KEY_0             to ButtonOrAxisSource.KEY_0,
        GLFW_KEY_A             to ButtonOrAxisSource.KEY_A,
        GLFW_KEY_B             to ButtonOrAxisSource.KEY_B,
        GLFW_KEY_C             to ButtonOrAxisSource.KEY_C,
        GLFW_KEY_D             to ButtonOrAxisSource.KEY_D,
        GLFW_KEY_E             to ButtonOrAxisSource.KEY_E,
        GLFW_KEY_F             to ButtonOrAxisSource.KEY_F,
        GLFW_KEY_G             to ButtonOrAxisSource.KEY_G,
        GLFW_KEY_H             to ButtonOrAxisSource.KEY_H,
        GLFW_KEY_I             to ButtonOrAxisSource.KEY_I,
        GLFW_KEY_J             to ButtonOrAxisSource.KEY_J,
        GLFW_KEY_K             to ButtonOrAxisSource.KEY_K,
        GLFW_KEY_L             to ButtonOrAxisSource.KEY_L,
        GLFW_KEY_M             to ButtonOrAxisSource.KEY_M,
        GLFW_KEY_N             to ButtonOrAxisSource.KEY_N,
        GLFW_KEY_O             to ButtonOrAxisSource.KEY_O,
        GLFW_KEY_P             to ButtonOrAxisSource.KEY_P,
        GLFW_KEY_Q             to ButtonOrAxisSource.KEY_Q,
        GLFW_KEY_R             to ButtonOrAxisSource.KEY_R,
        GLFW_KEY_S             to ButtonOrAxisSource.KEY_S,
        GLFW_KEY_T             to ButtonOrAxisSource.KEY_T,
        GLFW_KEY_U             to ButtonOrAxisSource.KEY_U,
        GLFW_KEY_V             to ButtonOrAxisSource.KEY_V,
        GLFW_KEY_W             to ButtonOrAxisSource.KEY_W,
        GLFW_KEY_X             to ButtonOrAxisSource.KEY_X,
        GLFW_KEY_Y             to ButtonOrAxisSource.KEY_Y,
        GLFW_KEY_Z             to ButtonOrAxisSource.KEY_Z,
        GLFW_KEY_GRAVE_ACCENT  to ButtonOrAxisSource.KEY_GRAVE,
        GLFW_KEY_MINUS         to ButtonOrAxisSource.KEY_MINUS,
        GLFW_KEY_EQUAL         to ButtonOrAxisSource.KEY_EQUAL,
        GLFW_KEY_LEFT_BRACKET  to ButtonOrAxisSource.KEY_LEFT_BRACKET,
        GLFW_KEY_RIGHT_BRACKET to ButtonOrAxisSource.KEY_RIGHT_BRACKET,
        GLFW_KEY_BACKSLASH     to ButtonOrAxisSource.KEY_BACKSLASH,
        GLFW_KEY_SEMICOLON     to ButtonOrAxisSource.KEY_SEMICOLON,
        GLFW_KEY_APOSTROPHE    to ButtonOrAxisSource.KEY_APOSTROPHE,
        GLFW_KEY_COMMA         to ButtonOrAxisSource.KEY_COMMA,
        GLFW_KEY_PERIOD        to ButtonOrAxisSource.KEY_PERIOD,
        GLFW_KEY_SLASH         to ButtonOrAxisSource.KEY_SLASH,
        GLFW_KEY_TAB           to ButtonOrAxisSource.KEY_TAB,
        GLFW_KEY_SPACE         to ButtonOrAxisSource.KEY_SPACE,
        GLFW_KEY_ESCAPE        to ButtonOrAxisSource.KEY_ESCAPE,
        GLFW_KEY_F1            to ButtonOrAxisSource.KEY_F1,
        GLFW_KEY_F2            to ButtonOrAxisSource.KEY_F2,
        GLFW_KEY_F3            to ButtonOrAxisSource.KEY_F3,
        GLFW_KEY_F4            to ButtonOrAxisSource.KEY_F4,
        GLFW_KEY_F5            to ButtonOrAxisSource.KEY_F5,
        GLFW_KEY_F6            to ButtonOrAxisSource.KEY_F6,
        GLFW_KEY_F7            to ButtonOrAxisSource.KEY_F7,
        GLFW_KEY_F8            to ButtonOrAxisSource.KEY_F8,
        GLFW_KEY_F9            to ButtonOrAxisSource.KEY_F9,
        GLFW_KEY_F10           to ButtonOrAxisSource.KEY_F10,
        GLFW_KEY_F11           to ButtonOrAxisSource.KEY_F11,
        GLFW_KEY_F12           to ButtonOrAxisSource.KEY_F12,
        GLFW_KEY_F13           to ButtonOrAxisSource.KEY_F13,
        GLFW_KEY_F14           to ButtonOrAxisSource.KEY_F14,
        GLFW_KEY_F15           to ButtonOrAxisSource.KEY_F15,
        GLFW_KEY_F16           to ButtonOrAxisSource.KEY_F16,
        GLFW_KEY_F17           to ButtonOrAxisSource.KEY_F17,
        GLFW_KEY_F18           to ButtonOrAxisSource.KEY_F18,
        GLFW_KEY_F19           to ButtonOrAxisSource.KEY_F19,
        GLFW_KEY_F20           to ButtonOrAxisSource.KEY_F20,
        GLFW_KEY_F21           to ButtonOrAxisSource.KEY_F21,
        GLFW_KEY_F22           to ButtonOrAxisSource.KEY_F22,
        GLFW_KEY_F23           to ButtonOrAxisSource.KEY_F23,
        GLFW_KEY_F24           to ButtonOrAxisSource.KEY_F24,
        GLFW_KEY_F25           to ButtonOrAxisSource.KEY_F25,
        GLFW_KEY_BACKSPACE     to ButtonOrAxisSource.KEY_BACKSPACE,
        GLFW_KEY_CAPS_LOCK     to ButtonOrAxisSource.KEY_CAPS_LOCK,
        GLFW_KEY_ENTER         to ButtonOrAxisSource.KEY_ENTER,
        GLFW_KEY_LEFT_SHIFT    to ButtonOrAxisSource.KEY_LEFT_SHIFT,
        GLFW_KEY_RIGHT_SHIFT   to ButtonOrAxisSource.KEY_RIGHT_SHIFT,
        GLFW_KEY_LEFT_CONTROL  to ButtonOrAxisSource.KEY_LEFT_CONTROL,
        GLFW_KEY_RIGHT_CONTROL to ButtonOrAxisSource.KEY_RIGHT_CONTROL,
        GLFW_KEY_LEFT_SUPER    to ButtonOrAxisSource.KEY_LEFT_SUPER,
        GLFW_KEY_RIGHT_SUPER   to ButtonOrAxisSource.KEY_RIGHT_SUPER,
        GLFW_KEY_LEFT_ALT      to ButtonOrAxisSource.KEY_LEFT_ALT,
        GLFW_KEY_RIGHT_ALT     to ButtonOrAxisSource.KEY_RIGHT_ALT,
        GLFW_KEY_MENU          to ButtonOrAxisSource.KEY_MENU,
        GLFW_KEY_INSERT        to ButtonOrAxisSource.KEY_INSERT,
        GLFW_KEY_DELETE        to ButtonOrAxisSource.KEY_DELETE,
        GLFW_KEY_HOME          to ButtonOrAxisSource.KEY_HOME,
        GLFW_KEY_END           to ButtonOrAxisSource.KEY_END,
        GLFW_KEY_PAGE_UP       to ButtonOrAxisSource.KEY_PAGE_UP,
        GLFW_KEY_PAGE_DOWN     to ButtonOrAxisSource.KEY_PAGE_DOWN,
        GLFW_KEY_UP            to ButtonOrAxisSource.KEY_UP,
        GLFW_KEY_DOWN          to ButtonOrAxisSource.KEY_DOWN,
        GLFW_KEY_LEFT          to ButtonOrAxisSource.KEY_LEFT,
        GLFW_KEY_RIGHT         to ButtonOrAxisSource.KEY_RIGHT,
        GLFW_KEY_NUM_LOCK      to ButtonOrAxisSource.KEY_NUM_LOCK,
        GLFW_KEY_KP_0          to ButtonOrAxisSource.KEY_NUMPAD_0,
        GLFW_KEY_KP_1          to ButtonOrAxisSource.KEY_NUMPAD_1,
        GLFW_KEY_KP_2          to ButtonOrAxisSource.KEY_NUMPAD_2,
        GLFW_KEY_KP_3          to ButtonOrAxisSource.KEY_NUMPAD_3,
        GLFW_KEY_KP_4          to ButtonOrAxisSource.KEY_NUMPAD_4,
        GLFW_KEY_KP_5          to ButtonOrAxisSource.KEY_NUMPAD_5,
        GLFW_KEY_KP_6          to ButtonOrAxisSource.KEY_NUMPAD_6,
        GLFW_KEY_KP_7          to ButtonOrAxisSource.KEY_NUMPAD_7,
        GLFW_KEY_KP_8          to ButtonOrAxisSource.KEY_NUMPAD_8,
        GLFW_KEY_KP_9          to ButtonOrAxisSource.KEY_NUMPAD_9,
        GLFW_KEY_KP_ADD        to ButtonOrAxisSource.KEY_NUMPAD_ADD,
        GLFW_KEY_KP_SUBTRACT   to ButtonOrAxisSource.KEY_NUMPAD_SUBTRACT,
        GLFW_KEY_KP_MULTIPLY   to ButtonOrAxisSource.KEY_NUMPAD_MULTIPLY,
        GLFW_KEY_KP_DIVIDE     to ButtonOrAxisSource.KEY_NUMPAD_DIVIDE,
        GLFW_KEY_KP_ENTER      to ButtonOrAxisSource.KEY_NUMPAD_ENTER,
        GLFW_KEY_KP_DECIMAL    to ButtonOrAxisSource.KEY_NUMPAD_DECIMAL
)

internal val mouseButtonToSource = mapOf(
        GLFW_MOUSE_BUTTON_1 to ButtonOrAxisSource.MOUSE_BUTTON_1,
        GLFW_MOUSE_BUTTON_2 to ButtonOrAxisSource.MOUSE_BUTTON_2,
        GLFW_MOUSE_BUTTON_3 to ButtonOrAxisSource.MOUSE_BUTTON_3,
        GLFW_MOUSE_BUTTON_4 to ButtonOrAxisSource.MOUSE_BUTTON_4,
        GLFW_MOUSE_BUTTON_5 to ButtonOrAxisSource.MOUSE_BUTTON_5,
        GLFW_MOUSE_BUTTON_6 to ButtonOrAxisSource.MOUSE_BUTTON_6,
        GLFW_MOUSE_BUTTON_7 to ButtonOrAxisSource.MOUSE_BUTTON_7,
        GLFW_MOUSE_BUTTON_8 to ButtonOrAxisSource.MOUSE_BUTTON_8
)