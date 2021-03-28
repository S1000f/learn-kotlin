package chapter3

// chapter 3: 함수 정의와 호출

val set = hashSetOf(1, 5, 44)
val list = arrayListOf(1, 4, 44)
val map = hashMapOf(1 to "one", 4 to "four", 44 to "forty-four")

fun chapter3() {
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    println(list.last())
    println(set.maxOrNull())

    val list1 = listOf(1, 2, 3)
    println(joinToString(list1, ";", "(", ")"))
    println(joinToString(list1, separator = ":", "{", "}"))
    // 일부만 인자전달
    println(joinToString(list1, ":"))
    // 모든 인자를 디폴트 사용시
    println(joinToString(list1))
    // 인자이름 지정할 경우 인자의 순서 상관없음
    println(joinToString(list1, postfix = "-", prefix = "#"))

    println("Kotlin".lastChar())
    println(list1.joinToString2(prefix = "=", postfix = "="))

    val view: View = Button()
    view.click()
    view.showOff()

    println("Kotlin".lastCharV2)
    val builder = java.lang.StringBuilder("Kotlin?")
    builder.lastCharV3 = '!'
    println(builder.toString().lastChar())

    println(sizeOf(1, 4, 5, 5))

    val arrayList = ArrayList<Int>()
    arrayList.add(3)
    arrayList.add(4)
    arrayList.add(1)

    val list = listOf("args: ", arrayList)
    println(list)

    // 중위호출 사용
    val map1 = mapOf(1 to "one", 2 to "two", 3 to "three")
    println(map1)

    val a = 3
    // 중위호출 사용: to 는 제네릭타입의 확장함수이다
    val infix = a to "three"
    println(infix)

    // Pair 는 코틀린 표준 라이브러리 함수이며, 두 원소의 쌍을 담은 자료구조이다
    val plussed: Pair<Int, Int> = a infixPlus 4
    // 구조 분해 선언: Pair 의 구조를 number1 과 number2 로 분해하여 선언하였다
    val (number1, number2) = a infixPlus 4
    println(plussed)
    println("${number1} :: ${number2}")
}

// 구조분해 선언
infix fun Int.infixPlus(that: Int) = Pair(this, this + that)

// 모든 인자에 대해 오버로딩한 자바 메소드를 추가해주는 어노테이션
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, value) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(value)
    }

    result.append(postfix)
    return result.toString()
}

// 확장함수: String 은 수신타입 this 는 수신객체 (this 는 생략가능)
fun String.lastChar(): Char = this[this.length - 1]

fun <T> Collection<T>.joinToString2(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = java.lang.StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

open class View {
    open fun click() = println("view clicked")
}

class Button : View() {
    override fun click() = println("button clicked")
}

fun View.showOff() = println("i am a View")
fun Button.showOff() = println("I am a Button")

// 확장 프로퍼티
val String.lastCharV2: Char
    get() = get(length - 1)

var java.lang.StringBuilder.lastCharV3: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

// 가변인자 함수: 파라미터명 앞에 vararg 키워드를 붙인다
fun sizeOf(vararg values: Int): Int = values.size
fun sizeOfList(value: List<Int>): Int = value.size