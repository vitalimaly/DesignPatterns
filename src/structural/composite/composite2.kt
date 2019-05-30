package structural.composite

import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val order = Order()
    // 1st item box
    val headphones = SomeItem()
    val boxForHeadphones = Box()
    boxForHeadphones.add(headphones)
    // 2nd item box with 2 boxes
    val boxForBoxes = Box()
    val phone = SomeItem()
    val boxForPhone = Box()
    boxForPhone.add(phone)
    val charger = SomeItem()
    val boxForCharger = Box()
    boxForCharger.add(charger)
    boxForBoxes.add(boxForPhone)
    boxForBoxes.add(boxForCharger)
    // 3rd some item
    val warranty = SomeItem()

    order.add(boxForHeadphones)
    order.add(boxForBoxes)
    order.add(warranty)

    println(order.getTotalPrice())
}

class Order {
    private var mainBox = Box()

    fun getTotalPrice(): Int {
        return mainBox.getPrice()
    }

    fun add(item: Item) {
        mainBox.items.add(item)
    }

    fun remove(item: Item) {
        mainBox.items.remove(item)
    }

    fun clear() {
        mainBox.items.clear()
    }
}

interface Item {
    fun getPrice(): Int
}

class Box : Item {
    var items = mutableListOf<Item>()

    override fun getPrice(): Int {
        return items.sumBy { it.getPrice() }
    }

    fun add(item: Item) {
        items.add(item)
    }
}

class SomeItem : Item {
    override fun getPrice(): Int {
        return Random.nextInt(1..99)
    }
}