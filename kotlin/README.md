
# Kotlin Tutorial: From Basics to Advanced

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)

This project shows Kotlin language features with simple code examples.
For more complete and advanced usage, check the .kt files in the [src](/01_basics/src/) folder.
This README.md is a quick reference to help you review the basics.

---

## Table of Contents

1. [Declaring Variables](#declaring-variables)
2. [String Operations](#string-operations)
3. [Null Safety](#null-safety)
4. [Conditionals](#conditionals)
5. [When Expressions](#when-expressions)
6. [Type Conversion](#type-conversion)
7. [Working with Arrays](#working-with-arrays)
8. [Lists](#lists)
9. [Loops and Iterations](#loops-and-iterations)
10. [Functions](#functions)
11. [Lambda Expressions](#lambda-expressions)
12. [Exception Handling](#exception-handling)
13. [Equality and Data Classes](#equality-and-data-classes)
14. [Generics](#generics)
15. [Object-Oriented Programming (OOP)](#object-oriented-programming-oop)
16. [Enums](#enums)
17. [Sealed Classes](#sealed-classes)
18. [Singleton Objects](#singleton-objects)

---

## Declaring Variables

Kotlin has **mutable** (`var`) and **immutable** (`val`) variable declarations:

```kotlin
val constantValue = 10L // Immutable (like Java's final or static final)
var counter = 20        // Mutable
```

Types can be inferred:

```kotlin
val name = "João"   // Inferred as String
val number = 10     // Inferred as Int
val decimal = 10.5F // Float
```

To declare nullable variables, append a `?`:

```kotlin
var maybeNull: Int? = null
```

Use `Any` for dynamic typing:

```kotlin
var anything: Any = 42
```

---

## String Operations

Kotlin strings offer powerful built-in functions:

```kotlin
val name = "João"
println(name[0])               // Access first character: 'J'
println(name.uppercase())      // "JOÃO"
println(name.lowercase())      // "joão"
println(name.isBlank())        // false
```

Multiline strings:

```kotlin
val message = """
    This
    is
    multiline
""".trimIndent()
```

---

## Null Safety

Kotlin avoids `NullPointerException` by design.

```kotlin
val name: String? = null
val result = name ?: "Anonymous"  // Elvis operator (returns default if null)
```

Use safe calls (`?.`) and null checks:

```kotlin
println(name?.length)  // Won't throw NPE
```

---

## Conditionals

Kotlin's `if-else` is an **expression**, not just a statement:

```kotlin
val score = 85
val grade = if (score >= 90) "A" else "B"
```

---

## When Expressions

`when` replaces `switch-case` with more flexibility:

```kotlin
val age = 18
val category = when (age) {
    in 13..18 -> "Teenager"
    in 19..59 -> "Adult"
    else -> "Child or Senior"
}
```

It can also return values:

```kotlin
val name = "Mario"
val isKnown = when (name) {
    "Mario", "Angela" -> true
    else -> false
}
```

You can even use conditions:

```kotlin
val result = when {
    age == 0 -> "Newborn"
    age < 0 -> "Invalid"
    else -> "Growing"
}
```

---

## Type Conversion

Types can be converted explicitly:

```kotlin
val number = 42
val double = number.toDouble()
val string = number.toString()
```

---

## Working with Arrays

```kotlin
val numbers = intArrayOf(1, 2, 3, 4)
numbers[0] = 10
println(numbers.contentToString())

val extended = numbers + 5  // Appends value
```

Null arrays:

```kotlin
val nullArray = arrayOfNulls<String>(5)
nullArray.fill("Hello")
```

---

## Lists

### Immutable List:

```kotlin
val fruits = listOf("Apple", "Banana", "Cherry")
println(fruits.first()) // Apple
println(fruits.last())  // Cherry
```

### Mutable List:

```kotlin
val mutable = mutableListOf("A")
mutable.add("B")
mutable.remove("A")
```

---

## Loops and Iterations

```kotlin
val names = listOf("João", "Lucas", "Maria")

// For-each
for (name in names) println(name)

// Index loop
for (i in names.indices) println("$i -> ${names[i]}")

// Ranges
for (i in 1..3) println(i)
for (i in 5 downTo 1 step 2) println(i) // 5, 3, 1

// forEach lambda
names.forEach { println(it) }

// Control flow
for (n in 1..10) {
    if (n == 3) continue
    if (n == 8) break
    println(n)
}
```

---

## Functions

### Basic:

```kotlin
fun greet(name: String = "Guest") {
    println("Hello, $name")
}
```

### Return values:

```kotlin
fun add(a: Int?, b: Int?): Int? {
    return if (a != null && b != null) a + b else null
}
```

### Multiple returns:

```kotlin
fun doubleReturn(): Pair<String, Int> = "Hi" to 2
fun tripleReturn(): Triple<String, Int, Double> = Triple("A", 1, 1.5)
```

### Varargs:

```kotlin
fun sumAll(vararg numbers: Int): Int = numbers.sum()
```

---

## Lambda Expressions

Passing behavior as parameters:

```kotlin
fun execute(action: () -> Unit) {
    println("Before")
    action()
    println("After")
}

execute {
    println("Doing something")
}
```

Lambda with parameter:

```kotlin
fun transform(fn: (Int) -> Int) {
    println(fn(5))
}
transform { it * 2 } // 10
```

---

## Exception Handling

Try-catch is also an expression:

```kotlin
val result = try {
    10 / 0
} catch (e: Exception) {
    null
}
```

---

## Equality and Data Classes

### Regular class compares memory:

```kotlin
class Rectangle(val width: Int, val height: Int)
val a = Rectangle(10, 20)
val b = Rectangle(10, 20)
println(a == b) // false
```

### Data class compares values:

```kotlin
data class Circle(val x: Float, val y: Float)
val c1 = Circle(1f, 2f)
val c2 = c1.copy(y = 3f)
println(c1 == c2) // false
```

---

## Generics

### Generic Function:

```kotlin
fun <T> describe(value: T): String = when (value) {
    is Int -> "Integer"
    is Float -> "Float"
    else -> "Unknown"
}
```

### Generic Class:

```kotlin
class Box<A, B>(val first: A, val second: B)
```

---

## Object-Oriented Programming (OOP)

### Classes and Inheritance:

```kotlin
open class Food(open var name: String)
class HotDog(override var name: String) : Food(name)
```

### Abstract Class:

```kotlin
abstract class YuGiOh {
    abstract fun typeIs(): String
}

class PotOfGreed : YuGiOh() {
    override fun typeIs() = "Spell"
}
```

### Interface:

```kotlin
interface Animal {
    fun speak(): String
}

class Dog : Animal {
    override fun speak() = "Woof!"
}
```

---

## Enums

Enums with custom properties and methods:

```kotlin
enum class Gender(val label: String, val letter: Char) {
    MALE("Male", 'M'),
    FEMALE("Female", 'F');

    fun description() = "Gender: $label, Letter: $letter"
}
```

Usage:

```kotlin
val gender = Gender.MALE
println(gender.description())
```

---

## Sealed Classes

Restricts inheritance to defined types in the same file. Useful with `when`.

```kotlin
sealed class Tool
class Hammer : Tool()
class Screwdriver : Tool()

fun describe(tool: Tool) = when (tool) {
    is Hammer -> "Used to hit"
    is Screwdriver -> "Used to screw"
}
```

---

## Singleton Objects

Singletons are created using `object`.

```kotlin
object Logger {
    fun log(msg: String) = println("Log: $msg")
}

Logger.log("Started app")
```

---