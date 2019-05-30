package creational.singleton

fun main() {
    App().start()
}

class App {
    fun start() {
        Database.query("SELECT ...")
        Database.query("SELECT * FROM ...")
    }
}

object Database {
    fun query(sql: String) {
        println("query: $sql")
    }
}