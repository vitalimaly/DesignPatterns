package creational.prototype

fun main() {
    val app = App()
    app.businessLogic()
}

class App {
    val shapes = mutableListOf<Shape>()

    init {
        val circle = Circle(20)
        circle.x = 10
        circle.y = 10
        shapes.add(circle)

        val anotherCircle = circle.clone()
        shapes.add(anotherCircle)

        val rectangle = Rectangle(10, 20)
        shapes.add(rectangle)
    }

    fun businessLogic() {
        val shapesCopy = mutableListOf<Shape>()
        for (shape in shapes) {
            shapesCopy.add(shape.clone())
        }
        println(shapes)
        println(shapesCopy)
    }
}

abstract class Shape(
        var x: Int = 0,
        var y: Int = 0,
        var color: String = "white"
) {
    constructor(source: Shape) : this(source.x, source.y, source.color)

    abstract fun clone(): Shape
}

class Rectangle : Shape {
    var width: Int = 0
    var height: Int = 0

    constructor(width: Int, height: Int) : super() {
        this.width = width
        this.height = height
    }

    constructor(source: Rectangle) : super(source)

    override fun clone(): Shape {
        return Rectangle(this)
    }
}

class Circle : Shape {
    var radius: Int = 0

    constructor(radius: Int) : super() {
        this.radius = radius
    }

    constructor(source: Circle) : super(source)

    override fun clone(): Shape {
        return Circle(this)
    }
}
