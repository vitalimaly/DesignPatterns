package creational.abstract_factory

fun main() {
    val config = Config.values().random()
    val factory = when {
        config.name == "Win" -> {
            WinFactory()
        }
        config.name == "Mac" -> {
            MacFactory()
        }
        else -> throw NotImplementedError()
    }
    val app = App(factory)
    app.createUI()
    app.paint()
}

class App(private val factory: GUIFactory) {
    private lateinit var button: Button

    fun createUI() {
        button = factory.createButton()
    }

    fun paint() {
        button.paint()
    }
}

enum class Config { Win, Mac }

interface GUIFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}

class WinFactory : GUIFactory {
    override fun createButton(): Button {
        return WinButton()
    }

    override fun createCheckbox(): Checkbox {
        return WinCheckbox()
    }
}

class MacFactory : GUIFactory {
    override fun createButton(): Button {
        return MacButton()
    }

    override fun createCheckbox(): Checkbox {
        return MacCheckbox()
    }
}

interface Checkbox {
    fun paint()
}

class WinCheckbox : Checkbox {
    override fun paint() {
        println("paint ${javaClass.simpleName}")
    }
}

class MacCheckbox : Checkbox {
    override fun paint() {
        println("paint ${javaClass.simpleName}")
    }
}

interface Button {
    fun paint()
}

class WinButton : Button {
    override fun paint() {
        println("paint ${javaClass.simpleName}")
    }
}

class MacButton : Button {
    override fun paint() {
        println("paint ${javaClass.simpleName}")
    }
}
