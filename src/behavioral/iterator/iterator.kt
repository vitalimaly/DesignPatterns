package behavioral.iterator

fun main() {
    val app = App()
    app.config(Facebook())
    app.sendSpamToFriends(1)
    app.sendSpamToCoworkers(1)
}

class App {
    lateinit var network: SocialNetwork
    lateinit var spammer: SocialSpammer

    fun config(socialNetwork: SocialNetwork) {
        when (socialNetwork) {
            is Facebook -> network = Facebook()
        }
        spammer = SocialSpammer()
    }

    fun sendSpamToFriends(profileId: Int) {
        val iterator = network.createFriendsIterator(profileId)
        spammer.send(iterator, "Funny message")
    }

    fun sendSpamToCoworkers(profileId: Int) {
        val iterator = network.createCoworkersIterator(profileId)
        spammer.send(iterator, "Very important message")
    }
}

class SocialSpammer {
    fun send(iterator: ProfileIterator, message: String) {
        while (iterator.hasNext()) {
            val profile = iterator.getNext()
            sendEmail(profile.getEmail(), message)
        }
    }

    private fun sendEmail(email: String, message: String) {
        println("to: $email, message: $message")
    }
}

interface SocialNetwork {
    fun createFriendsIterator(profileId: Int): ProfileIterator
    fun createCoworkersIterator(profileId: Int): ProfileIterator
}

class Facebook : SocialNetwork {
    override fun createFriendsIterator(profileId: Int): ProfileIterator {
        return FacebookIterator(this, profileId, "friends")
    }

    override fun createCoworkersIterator(profileId: Int): ProfileIterator {
        return FacebookIterator(this, profileId, "coworkers")
    }

    fun socialGraphRequest(profileId: Int, type: String): MutableList<Profile> {
        return mutableListOf(Profile(), Profile(), Profile())
    }
}

interface ProfileIterator {
    fun getNext(): Profile
    fun hasNext(): Boolean
}

class FacebookIterator(facebook: Facebook, profileId: Int, type: String) : ProfileIterator {
    var currentPosition: Int = 0
    val cache by lazy { facebook.socialGraphRequest(profileId, type) }

    override fun getNext(): Profile {
        return if (hasNext()) {
            cache[currentPosition++]
        } else {
            throw IndexOutOfBoundsException()
        }
    }

    override fun hasNext(): Boolean {
        return currentPosition < cache.size
    }
}

class Profile {
    fun getEmail(): String {
        return "q@q.q"
    }
}