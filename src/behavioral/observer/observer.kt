package behavioral.observer

fun main() {
    val app = App()
    app.config()
    app.doStuff()
}

class App {
    private val editor = Editor()

    fun config() {
        val logger = LoggingListener(
                "mylogs.txt",
                "Someone has opened file: %s")
        editor.events.subscribe("open", logger)

        val emailAlerts = EmailAlertsListener(
                "admin@me.com",
                "Someone has changed the file: %s")
        editor.events.subscribe("save", emailAlerts)
    }

    fun doStuff() {
        editor.openFile("README.md")
        editor.saveFile()
    }
}

class Editor {
    val events by lazy { EventManager() }
    private var file: String = ""

    fun openFile(path: String) {
        file = getFile(path)
        events.notify("open", file)
    }

    fun saveFile() {
        println("file saved")
    }

    private fun getFile(path: String): String {
        return "$path.txt"
    }
}

class EventManager {
    private val listeners = hashMapOf<String, EventListener>()

    fun subscribe(eventType: String, listener: EventListener) {
        listeners[eventType] = listener
    }

    fun unsubscribe(eventType: String, listener: EventListener) {
        listeners.remove(eventType)
    }

    fun notify(eventType: String, data: String) {
        for (entry in listeners) {
            entry.value.update(data)
        }
    }
}

interface EventListener {
    fun update(filename: String)
}

class LoggingListener(val logFilename: String, val message: String) : EventListener {
    override fun update(filename: String) {
        println("Logging to file $logFilename: ${message.format(filename)}")
    }
}

class EmailAlertsListener(val email: String, val message: String) : EventListener {
    override fun update(filename: String) {
        println("Sending email to $email: ${message.format(filename)}")
    }
}