// The const val is the equivalent to static final value in Java
const val staticFinalValue = 10L;

fun main(args: Array<String>) {

    // Creating variables
    var number: Int = 10;
    var text: String = "Hello world";
    var bigNumber: Long = 10L;
    var letter: Char = 'A';

    // You can use the "any" type to assign any value
    var number2: Any = 10;

    // Declaring the type is not necessary and can be redundant
    var number3 = 10; // Integer
    var number4 = 10F; // Float
    // and so on

    // Declaring constants
    val number5 = 10; // Cannot be changed once assigned
    println("Hello World! ${args.joinToString()}");

    // It's only possible to declare null values if the operator "?" is added
    // var nullValue = null // Not allowed directly
    var nullValue2: Int? = null;

    // Strings have several properties that can be accessed like:
    val name: String = "João";
    println("${name[0]} ${name.uppercase()} ${name.lowercase()} ${name.isBlank()}")

    var message: String? = """
        This
        Message
        Will be displayed according to this indentation
    """.trimIndent();
    println(message);

    // In Kotlin the == operator compares the values (content)
    val comparasion1: Boolean = "João" == "João" // true
    val comparisson2: Boolean = "João" === "Lucas" // false (compares references)

    println("${comparasion1}, ${comparisson2}");

    // Converting values. Each object has methods to convert itself to another type
    var numberInt: Int = 20;
    var numberConverted: Double = numberInt.toDouble();

    // The if-else statement is also an expression, so it returns a value
    var ifElseValue = if (1 > 2) {
        "absurd"
    } else {
        2
    }
    println(ifElseValue) // Will display 2

    // In Kotlin the ternary operator is replaced by the if-else expression.
    // Instead of using a == b ? 10 : 20, you use:
    var ternaryOperatorResult: Int? = if (20 > 40) 40 else null

    // Obs: If a block returns something, it's an expression. Otherwise, it's a statement.
    // The equivalent of switch-case in Kotlin is the when expression. And because it's an expression, it can return a result.
    // Below are two examples of how to use it:

    var personName: String = "Angela"

    // Example 1: simple branching
    when (personName) {
        "Angela" -> println("Female")
        "Mario" -> println("Male")
        else -> {
            println("No cataloged name")
        }
    }

    // Example 2: return a value
    var isAPersonName: Boolean = when (personName) {
        "Angela" -> true;
        "Mario" -> true;
        else -> false;
    }
    println(isAPersonName);

    // The when expression can also be used with ranges. Example below:
    var numberInRange: Int = 18
    when (numberInRange) {
        in 13..18 -> println("Teenager")
        !in 0..12 -> println("Adult")
        else -> println("Invalid Age")
    }

    // Declaring an array in Kotlin
    var arrayOfNumbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    arrayOfNumbers[2] = 10
    // Searching inside an array
    if (4 in arrayOfNumbers) println("Number 4 is inside arrayOfNumbers")
    // Once an array is declared, it can't be resized
    println(arrayOfNumbers.contentToString()) // You print all the content of the array in a single line

    // Creating a null array with fixed size and filling it with values
    var nullArray = arrayOfNulls<String>(10)
    nullArray.fill("Hello!")
    println(nullArray.contentToString())

    // Lists by default are immutable (can't add or remove elements)
    var myList = listOf<Double>(2.3, 3.4, 5.5)
    println(myList.first())
    println(myList.last())

    // Mutable lists allow you to add/remove elements
    // When declaring an empty mutable list, specify the type inside the diamond <>
    var myMutableList = mutableListOf<String>()

    myMutableList.add("First")
    myMutableList.removeAll { it == "First" }

    // Loops (for-each style)
    var sampleList = listOf("João", "Lucas", "Aquiles")
    for (name in sampleList) {
        name.replaceFirstChar { it -> it.uppercase() } // Does not change original
        println(name)
    }

    // Accessing by index (works for arrays and lists)
    for (index in sampleList.indices) {
        println("$index - ${sampleList[index]}")
    }

    // Limiting loops with ranges
    for (index in 1..2) {
        println(sampleList[index])
    }

    // Looping in reverse
    for (index in 2 downTo 1) {
        println(sampleList[index])
    }

    // Using step to skip iterations
    for (i in 0..10 step 2) {
        println("Even number: $i")
    }

    // Using the forEach operator
    sampleList.forEach({ it -> println(it) });

    // The operators continue and break are available, but only in non-functional loops like for, while, do-while
    var numberToBeInterecated = arrayOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    for (number in numberToBeInterecated) {
        if (number == 1) {
            break
        }
        if (number % 2 == 0) {
            continue
        }
        println("Odd and not 1: $number")
    } // Output will be: 3, 5, 7, 9

    // Passing a lambda function
    functinoWiwthALambadaAsArgument {
        println("Argument")
    }

    // Passing a lambda function with return value
    fucntionWithLambadaWithArgumentAsArgument({
        2
    });

    // Accessing multiple returned values
    var (value_a, value_b) = doubleReuturn1()
    println("$value_a $value_b")
    var (value_c, value_d, value_e) = tripeReturn()
    println("$value_c $value_d $value_e")

    // Using a class with custom getters/setters
    var firstClass: FirstClass? = FirstClass()
    firstClass?.age = 20
    println("age: ${firstClass?.age}, name: ${firstClass?.name}")

    // Using a constructor created manually
    var secondClassv1: SecondClass = SecondClass("joão", "joao", "joao@gmail.com");
    var secondClassv2: SecondClass = SecondClass(
        "name",
        "username",
        "email",
        Gender.MALE
    )

    secondClassv2.doSomethingWIhtNumbers(10)

    // Call methods from enum
    Gender.MALE.nameWithLetter()
    Gender.FEMALE.nameWithLetter()

    // You can also use comparisons in when expressions
    var number10: Int = 10
}


// A void function can have return type 'Unit', but it's unnecessary
fun doNothing(): Unit {
}

// You can create default values in function parameters,
// This way you can pass only the arguments you want.
fun functionWithDefaultValues(
    name: String? = null,
    username: String? = null,
    email: String? = null,
    age: Int? = null
) {
}

// Function that returns null if any argument is null
fun doSomethind(number1: Int?, number2: Int?): Int? {
    if (number1 == null || number2 == null) return null;
    return number1 + number2;
}

// It's also possible to pass a function as a lambda expression via parameter
// Below is an example how to pass a lambda function that returns Unit (void)
fun functinoWiwthALambadaAsArgument(lambda: () -> Unit) {
    println("Own function")
    lambda();
}

// Lambda with input and return value
fun fucntionWithLambadaWithArgumentAsArgument(lamba: (Int) -> Int) {
    val value = lamba(10)
    println("Valor é: $value ");
}

// Multiple returns in one function using Pair
fun doubleReuturn1(): Pair<String, Int> {
    return "Hello" to 10
}

// Or return a Triple
fun tripeReturn(): Triple<String, Int, Double> {
    return Triple("First", 2, 3.0)
}

// ?: is the Elvis operator, it provides a default value if the left-hand is null
// var nullValue : String? = null
// var a : String = nullValue ?: "Is Null"

// Declaring a class and manually defining getter and setter
class FirstClass {
    var name: String? = null
        get() = field ?: "Anonymous"
        set(value) {
            field = value
        }

    var age: Int? = null
        get() = field ?: 0
        set(value) {
            field = value
        }
}

// When creating an enum type, must be used like below
enum class Gender(
    val fullName: String,
    val letter: Char
) {
    MALE("male", 'M'), FEMALE("female", 'F');

    fun nameWithLetter() {
        println("name: ${this.fullName}, letter: ${this.letter}")
    }
}

// In Kotlin, if values are assigned inside the constructor,
// you can declare the 'var' directly there (no need to declare separately).
// Default values can be ignored, almost like a builder pattern.
class SecondClass(
    var name: String,
    var username: String,
    var email: String,
    var gender: Gender? = null
) {
    fun doSomethingWIhtNumbers(value: Int?): Int {
        val result = (value ?: 0) * 2
        return result
    }
}

// Kotlin has 4 visibility modifiers:
// public, internal, private, and protected
// Explained with example below:

// When a method is accessed in Kotlin, the JVM will create a getter and setter during compilation,
// so it won’t be direct access to the variable. It will use the getter/setter.
// You can't create a more permissive getter/setter than the variable itself.
// For example, if the field is private, you can’t create a public setter.
// But you can make it private, protected, or internal.
// 'field' is a reserved keyword, used to refer to the backing property
class Example {
    protected var name: String? = null
        get() {
            return if (field == "João") "John" else "Other Name"
        }
        private set(value) {
            field = if (value == "João") "John" else value
        }
}
