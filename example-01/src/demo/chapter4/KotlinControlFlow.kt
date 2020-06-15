package demo.chapter4

import java.util.*

/**
 * Created by system on 2017/8/14.
 * 控制流
 */
fun main(args: Array<String>) {
    //if - else 表达式
    val num1 = 1
    val num2 = 2
    val max = if (num1 > num2) num1 else num2
    println(max)
    println(if (num1 < num2) "if - else 表达式" else num2)

    //if的分支可以是代码块，最后的表达式作为该块的值：
    println(
            if (num1 < num2) {
                println("num1 < num2")
                "if - else 表达式"
            } else num2
    )

    //When 表达式
    when {
    } //最简单的形式

    val randomNum = Random().nextInt(5)
    when (randomNum) {
        0, 1 -> println("randomNum == 0 or randomNum == 1") //多个分支条件放在一起，用逗号分隔
        2 -> println("randomNum == 2")
        else -> println("otherwise")
    }

    when {
        randomNum == 0 -> {
            var a = 2; println("is 0") //;
            var b = 3
            println("is 0") //换行
        }
    }


//    如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
    when {
        randomNum == 0 -> println("randomNum is 0")
        randomNum == 1 -> println("randomNum is 1")
        else -> println("otherwise")
    }

    //when 作为一个表达式使用，则必须有 else 分支， 除非所有的可能情况都已经覆盖了。

    val args1 = "Hello"

    val an = when (1) {
        1 -> 1
        else -> "never arrive"
    }
    println(an)

    when (randomNum == 3) {
        true -> println("is 3")
        false -> println(randomNum)
    }

    println()

    val array = arrayOf(1, 2, 3)
    //for
    for (index in array.indices) print(array[index]);println() //索引遍历一个数组或者一个 list
    for (item in array) print(item);println()

    //库函数 forEachIndexed
    array.forEachIndexed { index, item -> print("[$index] = $item \t") }
    println()

    //库函数 withIndex
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

}