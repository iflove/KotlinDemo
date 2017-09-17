package demo.more

/**
 * Created by system on 2017/8/16.
 */

fun main(args: Array<String>) {
    val nothingInt/*: Nothing?*/ = null
    val list: List<Nothing?> = listOf(null)

    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
    fail("fail")

    //作为 Elvis 表达式的一部分
    var exception = null ?: throw RuntimeException("throw")

}