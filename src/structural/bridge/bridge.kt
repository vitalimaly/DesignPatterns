package structural.bridge

import kotlin.random.Random

fun main() {
    val tv = Tv()
    val remote = Remote(tv)
    remote.togglePower()

    val radio = Radio()
    val advancedRemote = AdvancedRemote(radio)
    advancedRemote.mute()
}

open class Remote(val device: Device) {
    fun togglePower() {
        if (device.isEnabled()) device.disable()
        else device.enable()
    }

    fun volumeDown() {
        device.setVolume(device.getVolume() - 10)
    }

    fun volumeUp() {
        device.setVolume(device.getVolume() + 10)
    }

    fun channelDown() {
        device.setChannel(device.getChannel() - 1)
    }

    fun channelUp() {
        device.setChannel(device.getChannel() + 1)
    }
}

class AdvancedRemote(device: Device) : Remote(device) {
    fun mute() {
        device.setVolume(0)
    }
}

interface Device {
    fun isEnabled(): Boolean
    fun enable()
    fun disable()
    fun getVolume(): Int
    fun setVolume(percent: Int)
    fun getChannel(): Int
    fun setChannel(channel: Int)
}

class Tv : Device {
    override fun isEnabled(): Boolean {
        return Random.nextBoolean()
    }

    override fun enable() {
    }

    override fun disable() {
    }

    override fun getVolume(): Int {
        return Random.nextInt(0, 100)
    }

    override fun setVolume(percent: Int) {
    }

    override fun getChannel(): Int {
        return Random.nextInt(999)
    }

    override fun setChannel(channel: Int) {
    }
}

class Radio : Device {
    override fun isEnabled(): Boolean {
        return Random.nextBoolean()
    }

    override fun enable() {
    }

    override fun disable() {
    }

    override fun getVolume(): Int {
        return Random.nextInt(0, 10)
    }

    override fun setVolume(percent: Int) {
    }

    override fun getChannel(): Int {
        return Random.nextInt(99)
    }

    override fun setChannel(channel: Int) {
    }
}
