package structural.proxy

import kotlin.random.Random

fun main() {
    val youtubeService = ThirdPartyYoutubeClass()
    val youtubeProxy = CachedYoutubeClass(youtubeService)
    val youtubeManager = YoutubeManager(youtubeProxy)
    youtubeManager.reactOnUserInput()
}

class YoutubeManager(val service: ThirdPartyYoutubeLib) {
    fun renderVideoPage(id: String) {
        println(service.getVideoInfo(id))
    }

    fun renderListPanel() {
        println(service.listVideos())
    }

    fun reactOnUserInput(id: String = "default") {
        renderVideoPage(id)
        renderListPanel()
    }
}

interface ThirdPartyYoutubeLib {
    fun listVideos(): List<Any>
    fun getVideoInfo(id: String): String
    fun downloadVideo(id: String)
}

class ThirdPartyYoutubeClass : ThirdPartyYoutubeLib {
    override fun listVideos(): List<Any> {
        return listOf(Any(), Any())
    }

    override fun getVideoInfo(id: String): String {
        return Random.nextBytes(10).contentToString()
    }

    override fun downloadVideo(id: String) {
        println("downloading")
    }
}

class CachedYoutubeClass(private val service: ThirdPartyYoutubeClass) : ThirdPartyYoutubeLib {
    private var listCache = listOf<Any>()
    private var videoInfoCache: String = ""
    var needReset: Boolean = false

    override fun listVideos(): List<Any> {
        if (listCache.isEmpty() || needReset) {
            listCache = service.listVideos()
        }
        return listCache
    }

    override fun getVideoInfo(id: String): String {
        if (videoInfoCache.isEmpty()) {
            videoInfoCache = service.getVideoInfo(id)
        }
        return videoInfoCache
    }

    override fun downloadVideo(id: String) {
        if (!downloadExists(id) || needReset) {
            service.downloadVideo(id)
        }
    }

    private fun downloadExists(id: String): Boolean {
        return Random.nextBoolean()
    }
}
