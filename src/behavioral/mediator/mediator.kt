package behavioral.mediator

import kotlin.random.Random

interface Mediator {
    fun notify(sender: Component, event: String)
}

class AuthenticationDialog : Mediator {
    private lateinit var title: String
    private lateinit var registrationUsername: String
    private lateinit var registrationPassword: String
    private val loginOrRegisterChkBx by lazy { Checkbox(this) }
    private val loginUsername by lazy { Textbox(this) }
    private val loginPassword by lazy { Textbox(this) }
    private val registrationEmail by lazy { Textbox(this) }
    private val okBtn by lazy { Button(this) }
    private val cancelBtn by lazy { Button(this) }

    override fun notify(sender: Component, event: String) {
        println("Log: sender ${sender.javaClass.simpleName}, event: $event")
        if (sender == loginOrRegisterChkBx && event == "check") {
            title = if (loginOrRegisterChkBx.isChecked()) "Log in" else "Register"
            println(title)
        }
        if (sender == okBtn && event == "click") {
            if (loginOrRegisterChkBx.isChecked()) {
                val user = findUser()
                if (user == null) {
                    println("User not found")
                } else {
                    println("User is $user")
                }
            } else {
                println("Go to register screen")
            }
        }
    }

    private fun findUser(): Boolean? {
        return Random.nextBoolean()
    }
}

open class Component(val dialog: Mediator) {
    fun click() {
        dialog.notify(this, "click")
    }

    fun keypress() {
        dialog.notify(this, "keypress")
    }
}

class Button(dialog: Mediator) : Component(dialog)

class Textbox(dialog: Mediator) : Component(dialog)

class Checkbox(dialog: Mediator) : Component(dialog) {
    fun check() {
        dialog.notify(this, "check")
    }

    fun isChecked(): Boolean {
        return Random.nextBoolean()
    }
}