package creational.builder

fun main() {
    App().makeCar()
}

class App {
    fun makeCar() {
        val director = Director()

        val carBuilder = CarBuilder()
        director.constructSportsCar(carBuilder)
        val car = carBuilder.car

        val carManualBuilder = CarManualBuilder()
        director.constructSportsCar(carManualBuilder)
        val manual = carManualBuilder.manual
    }
}

class Director {
    fun constructSportsCar(builder: Builder) {
        builder.reset()
        builder.setSeats(2)
        builder.setEngine("V8")
        builder.setTripComputer(true)
        builder.setGps(true)
    }
}

class Car(
    var seats: Int = 5,
    var engine: String = "V4",
    var hasTripComputer: Boolean = false,
    var hasGps: Boolean = false
)

class Manual(
    var seats: Int = 5,
    var engine: String = "V4",
    var hasTripComputer: Boolean = false,
    var hasGps: Boolean = false
)

interface Builder {
    fun reset()
    fun setSeats(seats: Int)
    fun setEngine(engine: String)
    fun setTripComputer(hasTripComputer: Boolean)
    fun setGps(hasGps: Boolean)
}

class CarBuilder : Builder {
    var car = Car()
        private set

    override fun reset() {
        car = Car()
    }

    override fun setSeats(seats: Int) {
        car.seats = seats
    }

    override fun setEngine(engine: String) {
        car.engine = engine
    }

    override fun setTripComputer(hasTripComputer: Boolean) {
        car.hasTripComputer = hasTripComputer
    }

    override fun setGps(hasGps: Boolean) {
        car.hasGps = hasGps
    }
}

class CarManualBuilder : Builder {
    var manual = Manual()
        private set

    override fun reset() {
        manual = Manual()
    }

    override fun setSeats(seats: Int) {
        manual.seats = seats
    }

    override fun setEngine(engine: String) {
        manual.engine = engine
    }

    override fun setTripComputer(hasTripComputer: Boolean) {
        manual.hasTripComputer = hasTripComputer
    }

    override fun setGps(hasGps: Boolean) {
        manual.hasGps = hasGps
    }
}
