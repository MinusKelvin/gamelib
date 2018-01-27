package minuskelvin.gamelib.input

import org.lwjgl.glfw.GLFW.*

internal val keysToInputSource = mapOf<Int, InputSource>(
        GLFW_KEY_SPACE         to ButtonSource.KEY_SPACE,
        GLFW_KEY_APOSTROPHE    to ButtonSource.KEY_APOSTROPHE,
        GLFW_KEY_COMMA         to ButtonSource.KEY_COMMA,
        GLFW_KEY_MINUS         to ButtonSource.KEY_MINUS,
        GLFW_KEY_PERIOD        to ButtonSource.KEY_PERIOD,
        GLFW_KEY_SLASH         to ButtonSource.KEY_SLASH,
        GLFW_KEY_0             to ButtonSource.KEY_0,
        GLFW_KEY_1             to ButtonSource.KEY_1,
        GLFW_KEY_2             to ButtonSource.KEY_2,
        GLFW_KEY_3             to ButtonSource.KEY_3,
        GLFW_KEY_4             to ButtonSource.KEY_4,
        GLFW_KEY_5             to ButtonSource.KEY_5,
        GLFW_KEY_6             to ButtonSource.KEY_6,
        GLFW_KEY_7             to ButtonSource.KEY_7,
        GLFW_KEY_8             to ButtonSource.KEY_8,
        GLFW_KEY_9             to ButtonSource.KEY_9,
        GLFW_KEY_SEMICOLON     to ButtonSource.KEY_SEMICOLON,
        GLFW_KEY_EQUAL         to ButtonSource.KEY_EQUALS,
        GLFW_KEY_A             to ButtonSource.KEY_A,
        GLFW_KEY_B             to ButtonSource.KEY_B,
        GLFW_KEY_C             to ButtonSource.KEY_C,
        GLFW_KEY_D             to ButtonSource.KEY_D,
        GLFW_KEY_E             to ButtonSource.KEY_E,
        GLFW_KEY_F             to ButtonSource.KEY_F,
        GLFW_KEY_G             to ButtonSource.KEY_G,
        GLFW_KEY_H             to ButtonSource.KEY_H,
        GLFW_KEY_I             to ButtonSource.KEY_I,
        GLFW_KEY_J             to ButtonSource.KEY_J,
        GLFW_KEY_K             to ButtonSource.KEY_K,
        GLFW_KEY_L             to ButtonSource.KEY_L,
        GLFW_KEY_M             to ButtonSource.KEY_M,
        GLFW_KEY_N             to ButtonSource.KEY_N,
        GLFW_KEY_O             to ButtonSource.KEY_O,
        GLFW_KEY_P             to ButtonSource.KEY_P,
        GLFW_KEY_Q             to ButtonSource.KEY_Q,
        GLFW_KEY_R             to ButtonSource.KEY_R,
        GLFW_KEY_S             to ButtonSource.KEY_S,
        GLFW_KEY_T             to ButtonSource.KEY_T,
        GLFW_KEY_U             to ButtonSource.KEY_U,
        GLFW_KEY_V             to ButtonSource.KEY_V,
        GLFW_KEY_W             to ButtonSource.KEY_W,
        GLFW_KEY_X             to ButtonSource.KEY_X,
        GLFW_KEY_Y             to ButtonSource.KEY_Y,
        GLFW_KEY_Z             to ButtonSource.KEY_Z,
        GLFW_KEY_LEFT_BRACKET  to ButtonSource.KEY_LEFT_BRACKET,
        GLFW_KEY_BACKSLASH     to ButtonSource.KEY_BACKSLASH,
        GLFW_KEY_RIGHT_BRACKET to ButtonSource.KEY_RIGHT_BRACKET,
        GLFW_KEY_GRAVE_ACCENT  to ButtonSource.KEY_GRAVE,
//        GLFW_KEY_WORLD_1       to ButtonSource.KEY_WORLD_1,
//        GLFW_KEY_WORLD_2       to ButtonSource.KEY_WORLD_2,
        GLFW_KEY_ESCAPE        to ButtonSource.KEY_ESCAPE,
        GLFW_KEY_ENTER         to ButtonSource.KEY_ENTER,
        GLFW_KEY_TAB           to ButtonSource.KEY_TAB,
        GLFW_KEY_BACKSPACE     to ButtonSource.KEY_BACKSPACE,
        GLFW_KEY_INSERT        to ButtonSource.KEY_INSERT,
        GLFW_KEY_DELETE        to ButtonSource.KEY_DELETE,
        GLFW_KEY_RIGHT         to ButtonSource.KEY_RIGHT,
        GLFW_KEY_LEFT          to ButtonSource.KEY_LEFT,
        GLFW_KEY_DOWN          to ButtonSource.KEY_DOWN,
        GLFW_KEY_UP            to ButtonSource.KEY_UP,
        GLFW_KEY_PAGE_UP       to ButtonSource.KEY_PAGE_UP,
        GLFW_KEY_PAGE_DOWN     to ButtonSource.KEY_PAGE_DOWN,
        GLFW_KEY_HOME          to ButtonSource.KEY_HOME,
        GLFW_KEY_END           to ButtonSource.KEY_END,
        GLFW_KEY_CAPS_LOCK     to ButtonSource.KEY_CAPS_LOCK,
//        GLFW_KEY_SCROLL_LOCK   to ButtonSource.KEY_SCROLL_LOCK,
        GLFW_KEY_NUM_LOCK      to ButtonSource.KEY_NUM_LOCK,
//        GLFW_KEY_PRINT_SCREEN  to ButtonSource.KEY_PRINT_SCREEN,
//        GLFW_KEY_PAUSE         to ButtonSource.KEY_PAUSE,
        GLFW_KEY_F1            to ButtonSource.KEY_F1,
        GLFW_KEY_F2            to ButtonSource.KEY_F2,
        GLFW_KEY_F3            to ButtonSource.KEY_F3,
        GLFW_KEY_F4            to ButtonSource.KEY_F4,
        GLFW_KEY_F5            to ButtonSource.KEY_F5,
        GLFW_KEY_F6            to ButtonSource.KEY_F6,
        GLFW_KEY_F7            to ButtonSource.KEY_F7,
        GLFW_KEY_F8            to ButtonSource.KEY_F8,
        GLFW_KEY_F9            to ButtonSource.KEY_F9,
        GLFW_KEY_F10           to ButtonSource.KEY_F10,
        GLFW_KEY_F11           to ButtonSource.KEY_F11,
        GLFW_KEY_F12           to ButtonSource.KEY_F12,
        GLFW_KEY_F13           to ButtonSource.KEY_F13,
        GLFW_KEY_F14           to ButtonSource.KEY_F14,
        GLFW_KEY_F15           to ButtonSource.KEY_F15,
        GLFW_KEY_F16           to ButtonSource.KEY_F16,
        GLFW_KEY_F17           to ButtonSource.KEY_F17,
        GLFW_KEY_F18           to ButtonSource.KEY_F18,
        GLFW_KEY_F19           to ButtonSource.KEY_F19,
        GLFW_KEY_F20           to ButtonSource.KEY_F20,
        GLFW_KEY_F21           to ButtonSource.KEY_F21,
        GLFW_KEY_F22           to ButtonSource.KEY_F22,
        GLFW_KEY_F23           to ButtonSource.KEY_F23,
        GLFW_KEY_F24           to ButtonSource.KEY_F24,
        GLFW_KEY_F25           to ButtonSource.KEY_F25,
        GLFW_KEY_KP_0          to ButtonSource.KEY_NUM_0,
        GLFW_KEY_KP_1          to ButtonSource.KEY_NUM_1,
        GLFW_KEY_KP_2          to ButtonSource.KEY_NUM_2,
        GLFW_KEY_KP_3          to ButtonSource.KEY_NUM_3,
        GLFW_KEY_KP_4          to ButtonSource.KEY_NUM_4,
        GLFW_KEY_KP_5          to ButtonSource.KEY_NUM_5,
        GLFW_KEY_KP_6          to ButtonSource.KEY_NUM_6,
        GLFW_KEY_KP_7          to ButtonSource.KEY_NUM_7,
        GLFW_KEY_KP_8          to ButtonSource.KEY_NUM_8,
        GLFW_KEY_KP_9          to ButtonSource.KEY_NUM_9,
        GLFW_KEY_KP_DECIMAL    to ButtonSource.KEY_NUM_PERIOD,
        GLFW_KEY_KP_DIVIDE     to ButtonSource.KEY_NUM_SLASH,
        GLFW_KEY_KP_MULTIPLY   to ButtonSource.KEY_NUM_STAR,
        GLFW_KEY_KP_SUBTRACT   to ButtonSource.KEY_NUM_MINUS,
        GLFW_KEY_KP_ADD        to ButtonSource.KEY_NUM_PLUS,
        GLFW_KEY_KP_ENTER      to ButtonSource.KEY_NUM_ENTER,
        GLFW_KEY_KP_EQUAL      to ButtonSource.KEY_NUM_ENTER,
        GLFW_KEY_LEFT_SHIFT    to ButtonSource.KEY_LEFT_SHIFT,
        GLFW_KEY_LEFT_CONTROL  to ButtonSource.KEY_LEFT_CONTROL,
        GLFW_KEY_LEFT_ALT      to ButtonSource.KEY_LEFT_ALT,
        GLFW_KEY_LEFT_SUPER    to ButtonSource.KEY_LEFT_SUPER,
        GLFW_KEY_RIGHT_SHIFT   to ButtonSource.KEY_RIGHT_SHIFT,
        GLFW_KEY_RIGHT_CONTROL to ButtonSource.KEY_RIGHT_CONTROL,
        GLFW_KEY_RIGHT_ALT     to ButtonSource.KEY_RIGHT_ALT,
        GLFW_KEY_RIGHT_SUPER   to ButtonSource.KEY_RIGHT_SUPER,
        GLFW_KEY_MENU          to ButtonSource.KEY_MENU
)

val mouseToInputSource = mapOf<Int, InputSource>(
        GLFW_MOUSE_BUTTON_1 to ButtonSource.MOUSE_1,
        GLFW_MOUSE_BUTTON_2 to ButtonSource.MOUSE_2,
        GLFW_MOUSE_BUTTON_3 to ButtonSource.MOUSE_3,
        GLFW_MOUSE_BUTTON_4 to ButtonSource.MOUSE_4,
        GLFW_MOUSE_BUTTON_5 to ButtonSource.MOUSE_5,
        GLFW_MOUSE_BUTTON_6 to ButtonSource.MOUSE_6,
        GLFW_MOUSE_BUTTON_7 to ButtonSource.MOUSE_7,
        GLFW_MOUSE_BUTTON_8 to ButtonSource.MOUSE_8
)