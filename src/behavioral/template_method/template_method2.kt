package behavioral.template_method

import java.io.File

abstract class DataMiner {
    fun mine(path: String) {
        val file = openFile(path)
        val rawData = extractData(file)
        val data = parseData(rawData)
        val analysis = analyzeData(data)
        sendReport(analysis)
        closeFile(file)
    }

    abstract fun openFile(path: String): File
    abstract fun extractData(file: File): ByteArray
    abstract fun parseData(rawData: ByteArray): String

    private fun analyzeData(data: String): String {
        return data.filterNot { it != '{' || it != '}' }
    }

    private fun sendReport(analysis: String) {
        println(analysis)
    }

    abstract fun closeFile(file: File)
}

class PDFDataMiner : DataMiner() {
    override fun openFile(path: String): File {
        return File(path)
    }

    override fun extractData(file: File): ByteArray {
        return file.readBytes()
    }

    override fun parseData(rawData: ByteArray): String {
        return rawData.contentToString()
    }

    override fun closeFile(file: File) {
        println("closing file ${file.name}")
    }
}

class DocDataMiner : DataMiner() {
    override fun openFile(path: String): File {
        return File(path)
    }

    override fun extractData(file: File): ByteArray {
        return file.readBytes()
    }

    override fun parseData(rawData: ByteArray): String {
        return rawData.contentToString()
    }

    override fun closeFile(file: File) {
        println("closing file ${file.name}")
    }
}