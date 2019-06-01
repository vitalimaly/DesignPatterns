package behavioral.command

import java.util.*

fun main() {
    val app = App()
    app.createUI()
}

class App {
    var clipboard: String? = null
    var activeEditor = Editor()
    val editors = mutableListOf(activeEditor)
    val shorcuts = Shortcuts()
    private val commandHistory = Stack<Command>()

    fun createUI() {
        val copy = { executeCommand(CopyCommand(this, activeEditor)) }
        val copyButton = Button(copy)
        shorcuts.onKeyPress("Ctrl+C", copy)

        val cut = { executeCommand(CutCommand(this, activeEditor)) }
        val cutButton = Button(cut)
        shorcuts.onKeyPress("Ctrl+X", cut)

        val paste = { executeCommand(PasteCommand(this, activeEditor)) }
        val pasteButton = Button(cut)
        shorcuts.onKeyPress("Ctrl+V", paste)

        val undo = { executeCommand(UndoCommand(this, activeEditor)) }
        val undoButton = Button(cut)
        shorcuts.onKeyPress("Ctrl+Z", undo)
    }

    fun executeCommand(command: Command) {
        if (command.execute()) {
            commandHistory.push(command)
        }
    }

    fun undo() {
        if (!commandHistory.empty()) {
            commandHistory.pop()?.undo()
        }
    }
}

class Editor {
    var text: String? = null

    fun getSelection(): String {
        return ('A'..'z').shuffled().subList(0, 5).joinToString("")
    }

    fun deleteSelection() {
        text?.replace(getSelection(), "")
    }

    fun replaceSelection(clipboard: String?) {
        clipboard?.let { text?.replace(getSelection(), it) }
    }
}

abstract class Command(val app: App, val editor: Editor) {
    var backup: String? = null

    fun saveBackup() {
        backup = editor.text
    }

    fun undo() {
        editor.text = backup
    }

    abstract fun execute(): Boolean // @return whether we need to save or not
}

class CopyCommand(app: App, editor: Editor) : Command(app, editor) {
    override fun execute(): Boolean {
        app.clipboard = editor.getSelection()
        return false
    }
}

class CutCommand(app: App, editor: Editor) : Command(app, editor) {
    override fun execute(): Boolean {
        saveBackup()
        app.clipboard = editor.getSelection()
        editor.deleteSelection()
        return false
    }
}

class PasteCommand(app: App, editor: Editor) : Command(app, editor) {
    override fun execute(): Boolean {
        saveBackup()
        editor.replaceSelection(app.clipboard)
        return false
    }
}

class UndoCommand(app: App, editor: Editor) : Command(app, editor) {
    override fun execute(): Boolean {
        app.undo()
        return false
    }
}

class Button(var command: () -> Unit)

class Shortcuts {
    private val shortcuts = mutableMapOf<String, () -> Unit>()

    fun onKeyPress(combination: String, command: () -> Unit) {
        shortcuts[combination] = command
    }
}