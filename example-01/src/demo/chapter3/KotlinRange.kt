package demo.chapter3

/**
 * Created by system on 2017/8/16.
 * 区间
 */
fun main(args: Array<String>) {
    println((1.rangeTo(3)).contains(1)) //使用区间rangeTo函数
    println(1 in (1..3)) //使用区间操作符

    println()

//    val intRange = 1..4 //step 1 default
    val intRange = 1..4 step 2 //step 2
    val is2 = 2 in intRange
    val is4 = 4 in intRange
    println("first = ${intRange.first},last = ${intRange.last},step = ${intRange.step}")
    println(is2)
    println(is4)
    println(is2 or is4)

//    for (index in 1..4) print(index)
    for (index in intRange) print(index)
    println()
    for (index in intRange.reversed()) print(index)
    println()

    for (index in 10..1) print(index) //Nothing
    println()
    val intProgression = 10 downTo 1 /*step 2*/ //step默认为1  倒序迭代
    println("first = ${intProgression.first},last = ${intProgression.last},step = ${intProgression.step}")
    for (index in intProgression) print(index)
    println()

    for (index in 1..4) print(index) // 输出“1234”

    val isIn = 3 in 1.rangeTo(100)
    println(isIn)

    for (i in 'a'..'z') print(i)

}