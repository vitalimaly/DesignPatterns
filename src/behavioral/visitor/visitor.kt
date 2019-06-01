package behavioral.visitor

fun main() {
    val dot = Dot(1, 10, 55)
    val circle = Circle(2, 23, 15, 10)
    val rectangle = Rectangle(3, 10, 17, 20, 30)

    val compoundShape = CompoundShape(4)
    compoundShape.add(dot)
    compoundShape.add(circle)
    compoundShape.add(rectangle)

    val c = CompoundShape(5)
    c.add(dot)
    compoundShape.add(c)

    val app = App()
    app.export(circle, compoundShape)
}

class App {
    fun export(vararg shapes: Shape) {
        val exportVisitor = XMLExportVisitor()
        println(exportVisitor.export(*shapes))
    }
}

interface Visitor {
    fun visitDot(d: Dot): String
    fun visitCircle(c: Circle): String
    fun visitRectangle(r: Rectangle): String
    fun visitCompoundShape(cs: CompoundShape): String
}

class XMLExportVisitor : Visitor {
    fun export(vararg shapes: Shape): String {
        val sb = StringBuilder()
        for (shape in shapes) {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n")
            sb.append(shape.accept(this)).append("\n")
            println(sb.toString())
            sb.setLength(0)
        }
        return sb.toString()
    }

    override fun visitDot(d: Dot): String {
        return "<d>" + "\n" +
                "    <id>" + d.id + "</id>" + "\n" +
                "    <x>" + d.x + "</x>" + "\n" +
                "    <y>" + d.y + "</y>" + "\n" +
                "</d>"
    }

    override fun visitCircle(c: Circle): String {
        return "<circle>" + "\n" +
                "    <id>" + c.id + "</id>" + "\n" +
                "    <x>" + c.x + "</x>" + "\n" +
                "    <y>" + c.y + "</y>" + "\n" +
                "    <radius>" + c.radius + "</radius>" + "\n" +
                "</circle>"
    }

    override fun visitRectangle(r: Rectangle): String {
        return "<rectangle>" + "\n" +
                "    <id>" + r.id + "</id>" + "\n" +
                "    <x>" + r.x + "</x>" + "\n" +
                "    <y>" + r.y + "</y>" + "\n" +
                "    <width>" + r.width + "</width>" + "\n" +
                "    <height>" + r.height + "</height>" + "\n" +
                "</rectangle>"
    }

    override fun visitCompoundShape(cs: CompoundShape): String {
        return "<compound_graphic>" + "\n" +
                "   <id>" + cs.id + "</id>" + "\n" +
                visitCompoundGraphic(cs) +
                "</compound_graphic>"
    }

    private fun visitCompoundGraphic(cs: CompoundShape): String {
        val sb = StringBuilder()
        for (shape in cs.children) {
            var obj = shape.accept(this)
            obj = "    " + obj.replace("\n", "\n    ") + "\n"
            sb.append(obj)
        }
        return sb.toString()
    }
}

interface Shape {
    fun move(x: Int, y: Int)
    fun draw()
    fun accept(v: Visitor): String
}

class Dot(val id: Int, val x: Int, val y: Int) : Shape {
    override fun move(x: Int, y: Int) {
    }

    override fun draw() {
    }

    override fun accept(v: Visitor): String {
        return v.visitDot(this)
    }
}

class Circle(val id: Int, val x: Int, val y: Int, val radius: Int) : Shape {
    override fun move(x: Int, y: Int) {
    }

    override fun draw() {
    }

    override fun accept(v: Visitor): String {
        return v.visitCircle(this)
    }
}

class Rectangle(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) : Shape {
    override fun move(x: Int, y: Int) {
    }

    override fun draw() {
    }

    override fun accept(v: Visitor): String {
        return v.visitRectangle(this)
    }
}

class CompoundShape(val id: Int) : Shape {
    val children = mutableListOf<Shape>()

    override fun move(x: Int, y: Int) {
    }

    override fun draw() {
    }

    override fun accept(v: Visitor): String {
        return v.visitCompoundShape(this)
    }

    fun add(shape: Shape) {
        children.add(shape)
    }
}
