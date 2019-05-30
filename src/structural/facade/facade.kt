package structural.facade

import kotlin.random.Random

fun main() {
    val convertor = VideoConverter()
    val mp4 = convertor.convert("youtubevideo.ogg", "mp4")
    convertor.save(mp4)
}

class VideoConverter {
    fun convert(filename: String, format: String): String {
        val file = VideoFile(filename)
        val sourceCodec = CodecFactory().extract(file)
        val destinationCodec = if (format == "mp4") MPEG4CompressionCodec() else OggCompressionCodec()
        val buffer = BitrateReader().read(filename, sourceCodec)
        var result = BitrateReader().convert(buffer, destinationCodec)
        result = AudioMixer().fix(result)
        return result.toString()
    }

    fun save(video: String) {
        println("saved")
    }

}

interface Codec

class OggCompressionCodec : Codec

class MPEG4CompressionCodec : Codec
class VideoFile(filename: String)
class CodecFactory {

    fun extract(file: VideoFile): Codec {
        val randomBoolean = Random.nextBoolean()
        return if (randomBoolean) OggCompressionCodec() else MPEG4CompressionCodec()
    }
}

class BitrateReader {
    fun read(filename: String, sourceCodec: Codec): ByteArray {
        return Random.nextBytes(10)
    }

    fun convert(buffer: ByteArray, destinationCodec: Codec): ByteArray {
        return Random.nextBytes(11)
    }
}

class AudioMixer {

    fun fix(result: ByteArray): ByteArray {
        return Random.nextBytes(12)
    }
}
