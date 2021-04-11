package chapter4

import org.omg.CORBA.Context
import java.io.Serializable
import javax.print.attribute.AttributeSet

fun chapter4() {
    val buttonClick = ButtonClick()
    buttonClick.click()
    buttonClick.setFocus(true)
    buttonClick.showOff()

    val milly = User4v4("milly")
    println(milly.isSubscribed)

    val sully = User4v4("Sully", false)
    println(sully.isSubscribed)

    val yeon = User4v4("Yeon", isSubscribed = true)
    println(yeon.isSubscribed)
}

interface Clickable {
    fun click()

    // 디폴트 메서드
    fun showOff() = println("I'm Clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus...")

    // 디폴트 메서드
    fun showOff() = println("I'm Focusable!!")
}

// 다중 인터페이스 구현
class ButtonClick : Clickable, Focusable {
    override fun click() = println("I was clicked")

    // 여러 인터페이스에 똑같은 디폴트 메소드 시그니쳐가 있다면, 그 디폴트 메서드를 구현 해야만 한다
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

// 상속에 열려있는 클래스: open 이 없다면 기본적으로 final 이다
open class RichButton : Clickable {
    // 클래스가 open 이라도 메소드에 open 이 없다면 final 이 된다.
    fun youCantOverrideIt() {}

    // 클래스가 open 이라도 메소드에 직접 open 을 붙여줘야 자식클래스에서 오버라이딩이 가능하다
    open fun overrideMe() = println("You can override it freely")

    // 인터페이스 구현 메소드는 기본적으로 open 이다. final 을 붙이면 자식 클래스는 click() 를 오버라이딩 할 수 없다
    override fun click() = println("You can also override this")
}

abstract class Animated {
    abstract fun animate()
    fun animateTwice() {
        println("It is moving...")
    }
    open fun stopAnimate() {
        println("It stopped")
    }
}

class Flower : Animated() {
    override fun animate() {
        TODO("Not yet implemented")
    }

    override fun stopAnimate() {
        super.stopAnimate()
    }
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk...")
    fun goOn() = println("Who")
}

internal fun TalkativeButton.giveSpeech() {
    // yell 은 프라이빗 접근자이므로 접근 불가능
//    yell()
    // whisper 는 protected 접근자 이므로 접근 불가능
//    whisper()
    // goOn 은 기본접근자 (public) 이므로 접근 가능
    goOn()
}

interface State: Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Button : View {
    val memberFiled = "outer"

    override fun getCurrentState(): State  = ButtonState()

    override fun restoreState(state: State) {
        super.restoreState(state)
    }

    // inner 변경자가 없는 경우 기본적으로 바깥 클래스인 Button 을 참조하지 않는다
    // 따라서 바깥 클래스인 Button 이 Serializable 을 구현하지 않았더라도, 그 중첩클래스인 ButtonState 만 따로 직렬화가 가능하다
    class ButtonState : State

    // inner 변경자: 바깥 클래스인 Button 을 참조하는 중첩클래스를 만들어 준다
    inner class Inner {
        val memberFiled = "inner"
        // inner 변경자가 있더라도 바깥 클래스인 Button 에 접근하려면 this@Button 형식으로 접근해야 함
        fun getOuterReference(): String = this@Button.memberFiled
    }
}

sealed class ExprClass {
    class Num(val value: Int) : ExprClass()
    class Sum(val left: ExprClass, val right: ExprClass) : ExprClass()
}

fun evals(e: ExprClass): Int = when (e) {
    is ExprClass.Num -> e.value
    is ExprClass.Sum -> evals(e.left) + evals(e.right)
}

// (val nickname: String) -> 주생성자
open class User4(val nickname: String)

// construct: 생성자 키워드, init: 초기화 블럭
class User4v2 constructor(nickname: String) {
    val nickname: String

    init {
        this.nickname = nickname
    }
}

// 초기화 블럭을 필드초기화와 합체, 어노테이션이나 가시성 변경자가 없다면 construct 키워드 생략가능
class User4v3(nickname: String) {
    val nickname = nickname
}

// 생성자 파라미터에도 디폴트값을 적용가능
class User4v4(val nickname: String, val isSubscribed: Boolean = true)

// 상속받은 부모 클래스에 생성자가 있는 경우 -> : 상속받은 클래스명(인자)
class TwitterUser(nickname: String) : User4(nickname)

// 아무런 인자가 없는 기본 생성자(default constructor)가 자동으로 만들어진다
open class Nothing
// 디폴트 생성자를 가진 클래스를 상속받을 때도 반드시 그 생성자를 호출해야만 한다
class Anything : Nothing()

// 이 클래스의 인스턴스화를 막기위해 하나뿐인 생성자에 private 변경자를 붙였다
class UtilityClass private constructor()

open class View4 {
    constructor(ctx: Context) {}

    constructor(ctx: Context, attr: String) {}
}

class MyButtons: View4 {
    constructor(ctx: Context) : this(ctx, "HELLO") {}

    constructor(ctx: Context, attr: String) : super(ctx, attr) {}
}