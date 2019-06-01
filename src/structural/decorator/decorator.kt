package structural.decorator

fun main() {
    val encoded = CompressionDecorator(
            EncryptionDecorator(
                    FileDataSource("readme.txt")
            ))
    encoded.writeData("Hello world!")
    println("encoded ${encoded.readData()}")
}

interface DataSource {
    fun writeData(data: String)
    fun readData(): String
}

class FileDataSource(val filename: String) : DataSource {
    override fun writeData(data: String) {
    }

    override fun readData(): String {
        return "some data"
    }
}

open class DataSourceDecorator(val wrappee: DataSource) : DataSource {
    override fun writeData(data: String) {
        wrappee.writeData(data)
    }

    override fun readData(): String {
        return wrappee.readData()
    }
}

class EncryptionDecorator(source: DataSource) : DataSourceDecorator(source) {
    override fun writeData(data: String) {
        super.writeData("$data*")
    }

    override fun readData(): String {
        val encryptedData = super.readData()
        return encryptedData.drop(1)
    }
}

class CompressionDecorator(source: DataSource) : DataSourceDecorator(source) {
    override fun writeData(data: String) {
        super.writeData(data.toLowerCase())
    }

    override fun readData(): String {
        return super.readData().toUpperCase()
    }
}