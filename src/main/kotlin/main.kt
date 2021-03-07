import java.util.*

fun main() {
    println("Hello World!")
    println(max(1, 2))
    println(max1(3, 6))
    println(max2(4, 5))
    println(initVal(13))

    valIsVal()
    stringTemplate(Collections.emptyList())
    stringTemplate(Collections.singletonList("Java"))
    stringTemplate(Arrays.asList("Java", "Kotlin"))
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
