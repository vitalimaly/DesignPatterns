package behavioral.strategy

fun main() {
    val app = App()
    app.start()
}

class App {
    private enum class StrategyType { Add, Subtract, Multiply }

    fun start() {
        val strategy = when (StrategyType.values().random()) {
            StrategyType.Add -> AddStrategy()
            StrategyType.Subtract -> SubtractStrategy()
            StrategyType.Multiply -> MultiplyStrategy()
        }
        val context = Context(strategy)
        val result = context.executeStrategy(2, 3)
        println(result)
    }
}

class Context(val strategy: Strategy) {
    fun executeStrategy(a: Int, b: Int): Int {
        return strategy.execute(a, b)
    }
}

interface Strategy {
    fun execute(a: Int, b: Int): Int
}

class AddStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int {
        return a + b
    }
}

class SubtractStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int {
        return a - b
    }
}

class MultiplyStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int {
        return a * b
    }
}
