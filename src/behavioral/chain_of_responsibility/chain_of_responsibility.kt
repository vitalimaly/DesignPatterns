package behavioral.chain_of_responsibility

fun main() {
    val app = App()
    app.createUI()
    app.onF1KeyPress()
}

class App {
    private val components = mutableListOf<Component>()

    fun createUI() {
        val dialog = Dialog("BudgetReports")
        dialog.wikiPageURL = "https://en.wikipedia.org/wiki/Budget"
        components.add(dialog)

        val panel = Panel(0, 0, 400, 800)
        panel.modalHelpText = "This panel does..."
        components.add(panel)

        val ok = Button(250, 760, 50, 20, "OK")
        ok.toolitpText = "This is an OK button that..."
        components.add(ok)
        val cancel = Button(320, 760, 50, 20, "Cancel")
        components.add(cancel)

        panel.add(ok)
        panel.add(cancel)
        dialog.add(panel)
    }

    fun onF1KeyPress() {
        val component = getComponentAtMouseCoords()
        component.showHelp()
    }

    private fun getComponentAtMouseCoords(): Component {
        return components.random()
    }
}

class Dialog(val text: String) : Container() {
    var wikiPageURL: String? = null

    override fun showHelp() {
        if (wikiPageURL != null) print("Open browser url: $wikiPageURL")
        else super.showHelp()
    }
}

class Panel(x: Int, y: Int, height: Int, width: Int) : Container() {
    var modalHelpText: String? = null

    override fun showHelp() {
        if (modalHelpText != null) print("Modal window: $modalHelpText")
        else super.showHelp()
    }
}

class Button(x: Int, y: Int, height: Int, width: Int, text: String) : Component()

abstract class Container : Component() {
    val children = mutableListOf<Component>()

    fun add(child: Component) {
        children.add(child)
        child.container = this
    }
}

interface ComponentWithContextualHelp {
    fun showHelp()
}

abstract class Component : ComponentWithContextualHelp {
    var toolitpText: String? = null
    var container: Container? = null

    override fun showHelp() {
        if (toolitpText != null) println(toolitpText)
        else container?.showHelp()
    }
}