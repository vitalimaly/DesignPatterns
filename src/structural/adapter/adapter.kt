package structural.adapter

import kotlin.math.sqrt

fun main() {
    val hole = RoundHole(5.0)
    val roundPeg = RoundPeg(5.0)
    println(hole.fits(roundPeg))

    val smallSquarePeg = SquarePeg(5.0)
    val largeSquarePeg = SquarePeg(10.0)

    val smallSquarePegAdapter = SquarePegAdapter(smallSquarePeg)
    val largeSquarePegAdapter = SquarePegAdapter(largeSquarePeg)
    println(hole.fits(smallSquarePegAdapter))
    println(hole.fits(largeSquarePegAdapter))
}

class RoundHole(val radius: Double) {
    fun fits(peg: RoundPeg): Boolean {
        return radius >= peg.radius
    }
}

open class RoundPeg(val radius: Double)

class SquarePeg(val width: Double)

class SquarePegAdapter(peg: SquarePeg) : RoundPeg(peg.width * sqrt(2.0) / 2)