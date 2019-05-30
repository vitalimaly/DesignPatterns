package creational.builder

class FoodOrder(
    val bread: String?,
    val condiments: String?,
    val meat: String?,
    val fish: String?) {

    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null) {

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun build() = FoodOrder(bread, condiments, meat, fish)
    }
}

class FoodOrder2 private constructor(builder: Builder2) {
    val bread: String?
    val condiments: String?
    val meat: String?
    val fish: String?

    init {
        this.bread = builder.bread
        this.condiments = builder.condiments
        this.meat = builder.meat
        this.fish = builder.fish
    }

    class Builder2 {
        var bread: String? = null
            private set
        var condiments: String? = null
            private set
        var meat: String? = null
            private set
        var fish: String? = null
            private set

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun build() = FoodOrder2(this)
    }
}