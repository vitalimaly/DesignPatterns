package structural.flyweight

fun main() {
    val forest = Forest()
    forest.plantTree(1, 1, "Oak", "brown", 0x11)
    forest.plantTree(1, 2, "Oak", "brown", 0x11)
    forest.plantTree(2, 1, "Oak", "brown", 0x11)
    forest.draw("canvas")
}

class Tree(val x: Int, val y: Int, val type: TreeType) {
    fun draw(canvas: String) {
        type.draw(canvas, x, y)
    }
}

class Forest {
    val trees = mutableListOf<Tree>()

    fun plantTree(x: Int, y: Int, name: String, color: String, textureResId: Int) {
        val type = TreeFactory.getTreeType(name, color, textureResId)
        val tree = Tree(x, y, type)
        trees.add(tree)
    }

    fun draw(canvas: String) {
        trees.forEach { it.draw(canvas) }
    }
}

class TreeType(val name: String, val color: String, val textureResId: Int) {
    fun draw(canvas: String, x: Int, y: Int) {
        println("draw")
    }
}

object TreeFactory {
    val treeTypes = mutableListOf<TreeType>()

    fun getTreeType(name: String, color: String, textureResId: Int): TreeType {
        var type = treeTypes.find { it.name == name && it.color == color && it.textureResId == textureResId }
        if (type == null) {
            type = TreeType(name, color, textureResId)
            treeTypes.add(type)
        }
        return type
    }
}
