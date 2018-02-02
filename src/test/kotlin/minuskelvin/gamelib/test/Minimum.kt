package minuskelvin.gamelib.test

import minuskelvin.gamelib.Application
import minuskelvin.gamelib.Screen
import minuskelvin.gamelib.Windowed
import minuskelvin.gamelib.math.Vector2i

class State(val app: Application) : Screen {
    override fun render(delta: Double) {
        app.inputHandler.tick()
    }

    override fun windowClose() {
        app.state = null
    }
}

fun main(args: Array<String>) {
    Application(Windowed(Vector2i(800, 600), "Minimum")).use { app ->
        app.state = State(app)
        app.run()
    }
}
