package behavioral.memento

class Editor {
    private var text: String = ""
    private var cursorX: Int = 0
    private var cursorY: Int = 0
    private var selectionWidth: Int = 0

    fun setText(text: String) {
        this.text = text
    }

    fun setCursor(cursorX: Int, cursorY: Int) {
        this.cursorX = cursorX
        this.cursorY = cursorY
    }

    fun setSelectionWidth(selectionWidth: Int) {
        this.selectionWidth = selectionWidth
    }

    fun createSnapshot(): Snapshot {
        return Snapshot(this, text, cursorX, cursorY, selectionWidth)
    }
}

class Snapshot(
        private val editor: Editor,
        private val text: String,
        private val cursorX: Int,
        private val cursorY: Int,
        private val selectionWidth: Int
) {
    fun restore() {
        editor.setText(text)
        editor.setCursor(cursorX, cursorY)
        editor.setSelectionWidth(selectionWidth)
    }
}

class Command(val editor: Editor) {
    private var backup: Snapshot? = null

    fun makeBackup() {
        backup = editor.createSnapshot()
    }

    fun undo() {
        backup?.restore()
    }
}