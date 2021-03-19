@file:Suppress("RemoveCurlyBracesFromTemplate")

package smartcast

import kotlin.IllegalArgumentException

// 여러 클래스를 중첩 클래스없이 선언할 수 있다. 따라서 파일 이름이 반드시 하나의 클래스이름일 필요가 없다
// 하지만 디렉터리 구조와 패키지 구조는 같게 만드는게 좋다
// 예를들어 이 파일은 smartcast 패키지에 속해있으므로, 프로젝트 디렉토리에 smartcast 디렉토리가 있고 그 안에 이 파일이 있어야 한다
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        // 스마트캐스트를 사용하지 않고 명시적 형변환 -bad
        val n = e as Num
        return n.value
    }

    // 스마트캐스트를 사용
    if (e is Sum) {
       return eval(e.right) + eval(e.left)
    }

    throw IllegalArgumentException("unknown expression")
}

// if 를 사용한 표현식 함수
fun evalV2(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        evalV2(e.left) + evalV2(e.right)
    } else {
        throw IllegalArgumentException("unknown expression")
    }

// when 을 사용한 표현식 함수
fun evalV3(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> evalV3(e.left) + evalV3(e.right)
        else -> throw IllegalArgumentException("unknown expression")
    }

// if, when 분기에서 블록사용. 블록에서 마지막 식의 값이 그 블록이 반환하는 값이다
fun evalWithLog(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLog(e.left)
            val right = evalWithLog(e.right)
            println("sum: ${left} + ${right}")
            left + right
        }
        else -> throw IllegalArgumentException("unknown expression")
    }