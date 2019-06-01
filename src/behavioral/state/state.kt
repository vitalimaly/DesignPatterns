package behavioral.state

class AudioPlayer {
    var state: State = ReadyState(this)
    var UI = UserInterface()
    var volume = 30
    var playlist = mutableListOf<String>()
    var currentSong = ""
    var isPlaying = false

    init {
        UI.lockButton.onClick { clickLock() }
        UI.playButton.onClick { clickPlay() }
        UI.nextButton.onClick { clickNext() }
        UI.prevButton.onClick { clickPrevious() }
    }

    fun changeState(state: State) {
        this.state = state
    }

    fun clickLock() {
        state.clickLock()
    }

    fun clickPlay() {
        state.clickPlay()
    }

    fun clickNext() {
        state.clickNext()
    }

    fun clickPrevious() {
        state.clickPrevious()
    }

    fun startPlayback() {
        println("startPlayback")
    }

    fun stopPlayback() {
        println("stopPlayback")
    }

    fun nextSong() {
        println("nextSong")
    }

    fun previousSong() {
        println("previousSong")
    }

    fun fastForward(time: Int) {
        println("fastForward  $time")
    }

    fun rewind(time: Int) {
        println("rewind $time")
    }
}

class UserInterface {
    var lockButton: Button = Button()
    var playButton: Button = Button()
    var nextButton: Button = Button()
    var prevButton: Button = Button()
}

class Button {
    fun onClick(action: () -> Unit) {
        action.invoke()
    }

}

abstract class State(var player: AudioPlayer) {
    abstract fun clickLock()
    abstract fun clickPlay()
    abstract fun clickNext()
    abstract fun clickPrevious()

}

class LockedState(player: AudioPlayer) : State(player) {
    override fun clickLock() {
        if (player.isPlaying) {
            player.changeState(PlayingState(player))
        } else {
            player.changeState(ReadyState(player))
        }
    }

    override fun clickPlay() {
    }

    override fun clickNext() {
    }

    override fun clickPrevious() {
    }

}

class ReadyState(player: AudioPlayer) : State(player) {
    override fun clickLock() {
        player.changeState(LockedState(player))
    }

    override fun clickPlay() {
        player.startPlayback()
        player.changeState(PlayingState(player))
    }

    override fun clickNext() {
        player.nextSong()
    }

    override fun clickPrevious() {
        player.previousSong()
    }

}

class PlayingState(player: AudioPlayer) : State(player) {
    override fun clickLock() {
        player.changeState(LockedState(player))
    }

    override fun clickPlay() {
        player.stopPlayback()
        player.changeState(ReadyState(player))
    }

    override fun clickNext() {
        player.nextSong()
    }

    override fun clickPrevious() {
        player.previousSong()
    }
}
