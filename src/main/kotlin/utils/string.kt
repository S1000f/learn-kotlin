@file:JvmName("StringUtils")
// StringUtils 라는 이름의 정적 유틸리티 클래스를 자동 생성해준다

package utils

// java: public static final String UNIX_LINE_SEPARATOR = "\n";
const val UNIX_LINE_SEPARATOR = "\n"

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