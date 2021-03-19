package exceptions

import java.io.BufferedReader
import java.lang.NumberFormatException

const val number = 100
// throw 는 표현식이므로 변수에 대입이 가능하다
val percentage =
    if (number in 0..100)
        number
    else
        throw IllegalArgumentException("a percentage must be between 0 and 100")

// checked exception 과 unchecked exception 의 구분이 없다.
// 자바처럼 IOException(checked exception) 의 처리를 강제하지 않는다.
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

// try, catch 역시 if, when 처럼 표현식이다. 따라서 변수에 대입 할 수 있다.
// if, when 과 마찬가지로 블록내의 마지막 값이 블록 전체의 대표값이다.
fun readNumberV2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }

    println(number)
}