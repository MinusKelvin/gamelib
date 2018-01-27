package minuskelvin.gamelib.test

import minuskelvin.gamelib.core.Game
import minuskelvin.gamelib.core.Updateable
import minuskelvin.gamelib.core.Windowed
import minuskelvin.gamelib.core.launch
import minuskelvin.gamelib.input.ButtonSource
import minuskelvin.gamelib.input.EventInput
import minuskelvin.gamelib.input.EventSource

class Context internal constructor(
        private val game: Game<Context>,
        val close: EventInput
) {
    var state
        get() = game.state
        set(value) { game.state = value }
}

class GameState: Updateable<Context> {
    override fun tick(context: Context) {
        if (context.close.state)
            context.state = null
    }

    override fun render(context: Context, alpha: Float) {
        
    }
}

fun main(args: Array<String>) {
    launch<Context>(args, Windowed("Minimal Test", 640, 480), 100.0) { game, input ->
        val ctx = Context(
                game = game,
                close = input.createEventInput()
        )
        ctx.close.bind(ButtonSource.KEY_ESCAPE)
        ctx.close.bind(EventSource.WINDOW_CLOSE)
        ctx.state = GameState()
        ctx
    }
}
