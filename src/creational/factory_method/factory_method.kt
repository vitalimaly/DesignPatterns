package creational.factory_method

fun main() {
    val app = App()
    app.initialize()
    app.dialog.render()
}

class App {
    lateinit var dialog: Dialog

    fun initialize() {
        val config = Config.values().random()
        when {
            config.name == "Windows" -> {
                dialog = WindowsDialog()
            }
            config.name == "Web" -> {
                dialog = WebDialog()
            }
            else -> throw NotImplementedError()
        }
    }
}

enum class Config { Windows, Web }

abstract class Dialog {
    fun render() {
        val okButton = createButton()
        okButton.onClick()
        okButton.render()
    }

    abstract fun createButton(): Button
}

class WindowsDialog : Dialog() {
    override fun createButton(): Button {
        return WindowsButton()
    }
}

class WebDialog : Dialog() {
    override fun createButton(): Button {
        return HtmlButton()
    }
}

interface Button {
    fun render()
    fun onClick()
}

class WindowsButton : Button {
    override fun render() {
        println("render ${javaClass.simpleName}")
    }

    override fun onClick() {
        println("onClick ${javaClass.simpleName}")
    }
}

class HtmlButton : Button {
    override fun render() {
        println("render ${javaClass.simpleName}")
    }

    override fun onClick() {
        println("onClick ${javaClass.simpleName}")
    }
}
