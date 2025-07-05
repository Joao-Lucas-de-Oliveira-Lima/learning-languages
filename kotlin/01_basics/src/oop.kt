import java.math.BigDecimal

fun main() {
    // Input reading. Reading user input from console
    var input = readln()

    println(input)

    // It's possible to simplify nested if-else blocks using the "when" expression.
    // Example below:
    // Since toIntOrNull() is used, there's no need for a safe call operator (?.)
    var inputAsANumber = input.toIntOrNull() ?: 0

    var result: String = when {
        inputAsANumber == 0 -> "Equals to zero"
        inputAsANumber > 0 -> "Greater than 0"
        else -> "Less than 0"
    }
    println(result)

    // Using try-catch as an expression. Like if-else and when, it returns a value.
    var number = 10

    var dividedByZero = try {
        number / 0
    } catch (e: Exception) {
        // throw RuntimeException("Cannot divide by 0", e)
        null // Optional: handle the error gracefully
    }

    // Arrays can be changed at runtime by creating a new one.
    // The += operator appends a value, creating a new array with size +1.
    var array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    array += 2
    array[10] = 11
    println(array[10])

    // The equals (==) operator by default compares references in normal classes,
    // so even if attributes are equal, objects are different unless you override equals().
    var rectangle1: Rectangle = Rectangle(height = 10, width = 20)
    var rectangle2: Rectangle = Rectangle(height = 10, width = 20)

    println(rectangle1 == rectangle2) // false, different memory references

    // For simple data holders, it's better to use data classes,
    // which automatically implement equals(), hashCode(), toString(), and copy().
    var circle1: Circle = Circle(x = 10.0F, y = 20.0F)
    var circle2: Circle = Circle(x = 10.0F, y = 20.0F)

    println(circle1 == circle2) // true, values compared instead of references
    println(circle1)            // pretty-printed using toString()

    var circle3 = circle1.copy(y = 30f) // Create a modified copy (Prototype pattern)

    // Creating a Dog (interface + class inheritance)
    var pitBull: Animal = Dog("black", 2)

    // You can use the vararg keyword to pass a variable number of arguments to a function
    println(doMathSum(10, 20, 30))
    println(doMathSum(2, 3, 4, 5, 2, 9))

    // Creating an instance from an abstract class using a subclass
    var potOfGreed: YuGiOh = PotOfGreed("potOfGreed", 1)
    println(potOfGreed.typeIs())

    // Calling a method from a singleton without instantiating
    singleton.doSomething()
}

// Regular class (no equals/hashCode/toString override)
class Rectangle(val width: Int, val height: Int)

// Data class used to store values with utility functions
data class Circle(val x: Float, val y: Float)


// Interface with attributes and methods to override
interface Animal {
    var _name: String
    var height: Int
    val category: String
    fun produceSound(sound: String): String
}

// Implementing the Animal interface
class Dog(
    var furColor: String,
    var earsNumber: Int,
) : Animal {
    override var _name: String = "Dog"
    override var height: Int = 10
    override val category: String = "Animal"

    override fun produceSound(sound: String): String {
        return "$_name makes sound: $sound"
    }
}

// Function that receives a variable number of arguments using vararg
fun doMathSum(vararg values: Int): Int {
    return values.sumOf { it }
}

// Abstract class allows partial implementation and mandatory overrides
abstract class YuGiOh {
    abstract var name: String
    abstract var id: Int
    abstract fun typeIs(): String

    protected val helloWorldString: String = "HelloWorld"

    fun helloWorld() {
        println(helloWorldString)
    }
}

// Subclass that implements all abstract members
class PotOfGreed(
    override var name: String,
    override var id: Int
) : YuGiOh() {
    override fun typeIs(): String {
        return "Spell"
    }
}

// If you want a class to be extendable, it must be marked with 'open'
// Same for any function or property that should be overrideable
open class Food(
    open var name: String,
    open var price: BigDecimal
) {
    open fun printPrice() {
        println(price)
    }
}

// Subclass overriding a method and calling super
class HotDog(
    override var name: String,
    override var price: BigDecimal
) : Food(name, price) {
    override fun printPrice() {
        super.printPrice() // Calling the original method
        println("Overridden price value: $price")
    }
}

// Sealed class is used to restrict subclassing (all subclasses must be in the same file)
// They are useful with when expressions because the compiler enforces exhaustiveness.
sealed class Tool(var name: String)

sealed class Screwdriver(name: String) : Tool(name)
sealed class Hammer(name: String) : Tool(name)

// TODO: Add a when expression example using sealed classes

// Generic function to work with different types
fun <T> verifyIfANumber(number: T): String {
    return when (number) {
        is Int -> "Integer"
        is Float -> "Float"
        is Double -> "Double"
        else -> "Not a number"
    }
}

// Generic class accepting two types
class ClassWithGeneric<D, T>(
    val dValue: D,
    val tValue: T
)

// Singleton object in Kotlin (only one instance globally available)
object singleton {
    val int: String = ""
    fun doSomething() {
        println("Singleton function called")
    }
}
