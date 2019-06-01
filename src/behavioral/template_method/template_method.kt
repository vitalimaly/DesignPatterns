package behavioral.template_method

import kotlin.random.Random
import kotlin.random.nextInt

abstract class GameAI {
    var builtStructures = mutableListOf<Building>()
    var map = GameMap()
    var availableResources = 100
    var scouts = 0
    var warriors = 0

    fun turn() {
        collectResources()
        buildNewStructures()
        buildUnits()
        attack()
    }

    open fun collectResources() {
        builtStructures.forEach { it.collect() }
    }

    open fun attack() {
        val enemy = closestEnemy()
        if (enemy == null) {
            sendScouts(map.center)
        } else {
            sendWarriors(enemy.position)
        }
    }

    abstract fun buildNewStructures()

    abstract fun buildUnits()

    abstract fun sendScouts(position: Pair<Int, Int>)

    abstract fun sendWarriors(position: Pair<Int, Int>)

    private fun closestEnemy(): Enemy? {
        return if (Random.nextBoolean()) Enemy() else null
    }

    protected fun moveUnits(unit: Int, position: Pair<Int, Int>) {
        println("moving $unit to $position")
    }
}

class OrcsAI : GameAI() {
    override fun buildNewStructures() {
        if (availableResources > 0) {
            println("building a structure")
            availableResources -= 10
        }
    }

    override fun buildUnits() {
        if (availableResources > 0) {
            if (scouts < 10) {
                println("building units")
                availableResources -= 10
            } else {
                println("building warriors")
                availableResources -= 20
            }
        }
    }

    override fun sendScouts(position: Pair<Int, Int>) {
        if (scouts > 0) moveUnits(scouts, position)
    }

    override fun sendWarriors(position: Pair<Int, Int>) {
        if (warriors > 5) moveUnits(warriors, position)
    }
}

class MonstersAI : GameAI() {
    override fun collectResources() {
    }

    override fun attack() {
    }

    override fun buildNewStructures() {
    }

    override fun buildUnits() {
        if (availableResources > 0) {
            println("building warriors")
            availableResources -= 15
        }
    }

    override fun sendScouts(position: Pair<Int, Int>) {
        if (scouts > 0) moveUnits(scouts, position)
    }

    override fun sendWarriors(position: Pair<Int, Int>) {
        if (warriors > 0) moveUnits(warriors, position)
    }
}

class GameMap {
    val x: Int = 0
    val y: Int = 0
    val width: Int = 100
    val height: Int = 100
    val center = height / 2 to width / 2
}

class Building {
    fun collect() {
        println("Collected ${Random.nextInt(1..10)} resources")
    }
}

class Enemy {
    val position = Random.nextInt(0..100) to Random.nextInt(0..100)
}