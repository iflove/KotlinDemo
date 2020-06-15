package demo.more

/**
 * Created by system on 2017/9/17.
 * kotlin 操作符
 */


data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

val point = Point(10, 20)


fun main(args: Array<String>) {
    println(-point)  // 输出“(-10, -20)”

}