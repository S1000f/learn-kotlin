import chapter3.chapter3
import chapter4.chapter4
import chapter5.chapter5
import smartcast.Num
import smartcast.Sum
import smartcast.eval
import java.lang.Exception
import java.util.*

fun main() {
//    chapter2()
//    chapter3()
//    chapter4()
    chapter5()
}

fun chapter2() {
    println("Hello World!")
    println(max(1, 2))
    println(max1(3, 6))
    println(max2(4, 5))
    println(initVal(13))

    valIsVal()
    stringTemplate(Collections.emptyList())
    stringTemplate(Collections.singletonList("Java"))
    stringTemplate(Arrays.asList("Java", "Kotlin"))

    // instant 생성 시 new 가 필요없음
    val person = Person("kdh", false)
    println("is \"${person.name}\" married? : ${person.isMarried}")

    println(Rectangle(3, 3).isSquare)

    println(Color.BLUE.rgb())
    println(getMnemonic(Color.GREEN))
    println(getWarmth(Color.YELLOW))
    println(mix(Color.YELLOW, Color.RED))

    println(eval(Sum(Sum(Num(2), Num(4)), Num(3))))

    for (i in 1..100) {
        print(fizzBuzz(i))
    }

    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
    println()

    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary // java: binaryReps.put(c, binary);
    }
    for ((letter, binary) in binaryReps) {
        println("${letter} = ${binary}")
    }

    val list = arrayListOf("10", "101", "203")
    for ((index, element) in list.withIndex()) {
        println("${index} : ${element}")
    }

    println(isLetter('f'))
    println(isNotDigit('4'))

    // in 으로 검사하기 - Comparable 를 구현한 클래스는 범위를 만들수있음. 하지만 루프는 돌 수 없음
    println("Kotlin" in "Java".."Scala")
    println("a" in setOf("a", "b", "c"))
}

// 블록 함수
fun max(a: Int, b: Int): Int {
    // 코틀린의 대부분의 제어구조는 문이 아니라 표현식이다. 자바처럼 if '문'을 괄호로 묶는 방식이 아님
    return if (a > b) a else b
}

// 표현식 함수
fun max1(a: Int, b: Int): Int = if (a > b) a else b

// 표현식 함수는 반환값을 타입추론 할 수 있으므로 반환타입을 생략 할 수 있다 (블록 함수는 불가능)
fun max2(x: Int, y: Int) = if (x > y) x else y

val question = "an ultimate question for life, the universe and everything"
val answer = 42
val theAnswer: Int = 42

fun initVal(x: Int): String {
    val message: String
    // val 은 한번만 초기화 될 수 있지만, if 조건으로 한 번만 초기화되는 상황이라면 여러 값으로 초기화 될 수 있다
    if (x > 10) {
        message = "success"
    } else {
        message = "failed"
    }

    return message
}

fun valIsVal() {
    // val 의 참조 자체는 불변이지만, 그 참조가 가리키는 객체(arrayList)의 내부값은 변경될 수 있음
    val arrays = arrayListOf("Java")
    println(arrays)
    arrays.add("Kotlin")
    println(arrays)
}

fun stringTemplate(args: MutableList<Any>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    // 중괄호 생략 가능
    println("Hello, $name!")
    // 식별자가 영문이 아닌경우 중괄호 생략불가 (그냥 항상 중괄호를 쓰는 습관이 좋다)
    println("안녕하세요 ${name}님!")
    // 중괄호 안에서 연산 가능
    println("Hello, ${if (args.size == 1) "Sir " + name else "everyone"} again!")

}

// 값 객체
// public 이 기본이므로 생략 가능
class Person(
    // val 은 final 선언된 자바 필드와 유사
    // Getter 를 자동생성
    val name: String,
    // var 는 Getter 와 Setter 모두 자동 생성
    var isMarried: Boolean
)

class Rectangle(private val height: Int, private val width: Int) {
    val sum
        get() = height + width
    val isSquare: Boolean
        get() = height == width
}

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255);

    fun rgb() = (r * 256 + g) * 256 + b
}

// when 역시 if 와 같은 표현식이므로 함수의 반환값으로 지정가능
fun getMnemonic(color: Color) = when (color) {
    Color.RED -> "Richard"
    Color.ORANGE -> "Of"
    Color.YELLOW -> "York"
    Color.BLUE -> "Battle"
    Color.GREEN -> "Gave"
}

fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    else -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
    setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
    else -> throw Exception("not matching")
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "${i}"
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It is a digit"
    in 'a'..'z', in 'A'..'Z' -> "It is a letter!"
    else -> "I don't know..."
}