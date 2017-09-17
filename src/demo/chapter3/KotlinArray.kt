package demo.chapter3

/**
 * Created by system on 2017/8/13.
 * 数组
 */

fun main(args: Array<String>) {
    val args: Array<Int> = arrayOf(1, 2, 3)
    val arrayOfNulls = arrayOfNulls<Int>(10) //空数组
    val initArray = Array(5, { i -> (i * i).toString() }) //构造函数init

    println(arrayOfNulls.size)
    println()

    //数组是不型变的（invariant)
    val argsAny: Array<out Any> = args


    //iterator
    val iterator = args.iterator()
    while (iterator.hasNext()) {
        print("" + iterator.next())
    }
    println()
    //forEach
    args.iterator().forEach { print(it) }
    println()

    //for-
    for (it in initArray/*.iterator()*/) {
        print(it)
    }
    println()
    //下标索引
    args.forEachIndexed { index, i -> println("$index = $i") }

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]

}