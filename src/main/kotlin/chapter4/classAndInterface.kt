package chapter4

fun chapter4() {
    val buttonClick = ButtonClick()
    buttonClick.click()
    buttonClick.setFocus(true)
    buttonClick.showOff()

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