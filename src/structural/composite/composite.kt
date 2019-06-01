package structural.composite

fun main() {
    val imageEditor = ImageEditor()
    imageEditor.load()
    imageEditor.groupSelected(listOf(
            Dot(3, 3),
            Dot(5, 5),
            Circle(3, 3, 3)))
}

class ImageEditor {
    val all = CompoundGraphic()

    fun load() {
        all.add(Dot(1, 2))
        all.add(Circle(5, 3, 10))
    }

    fun groupSelected(components: List<Graphic>) {
        val group = CompoundGraphic()
        group.add(components)
        all.remove(components)
        all.add(group)
        all.draw()
    }
}

interface Graphic {
    fun move(x: Int, y: Int)
    fun draw()
}

class CompoundGraphic : Graphic {
    var children = mutableListOf<Graphic>()

    fun add(child: Graphic) {
        children.add(child)
    }

    fun add(list: List<Graphic>) {
        list.forEach { children.add(it) }
    }

    fun remove(child: Graphic) {
        children.remove(child)
    }

    fun remove(list: List<Graphic>) {
        list.forEach { children.remove(it) }
    }

    override fun move(x: Int, y: Int) {
        for (child in children) {
            child.move(x, y)
        }
    }

    override fun draw() {
        for (child in children) {
            child.draw()
        }
    }
}

open class Dot(var x: Int, var y: Int) : Graphic {
    override fun move(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    override fun draw() {
        println("$x, $y")
    }
}

class Circle(x: Int, y: Int, var radius: Int) : Dot(x, y) {
    override fun draw() {
        println("$x, $y, radius $radius")
    }
}