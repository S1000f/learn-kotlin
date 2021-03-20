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

}

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
