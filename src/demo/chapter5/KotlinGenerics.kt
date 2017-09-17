package demo.chapter5

import kotlin.test.assertEquals
import kotlin.collections.List as KList

/**
 * Created by system on 2017/9/3.
 * 泛型
 */

fun main(args: Array<String>) {

    val emptyListString = List<String>()
    val listString = List("C", "D")
    assertEquals(0, emptyListString.size, "empty")
    printList(listString)

    val stringList = listOf<String>()
    val anyList: KList<Any> = stringList

    //使用处型变：类型投影
    val ints: Array<out Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }

    //out 生产者 相当于Java ? extends T
    fun copy(from: Array<out Any>, to: Array<Any>) {
        for (index in from.indices) {
            to[index] = from[index]
        }
    }
    copy(from = ints, to = any)
    for (items in any) {
        println(items)
    }

    //out 消费者 相当于Java ? super T
    fun fill(dest: Array<in Int>, value: Int) {
        for (index in dest.indices) {
            dest[index] = (dest[index] as? Int)!!.times(value)
        }
    }
    fill(any, 2)
    for (items in any) {
        println(items)
    }

    //星投影
    val star: KList<*> = listOf("C", "D", 1, 2)
    val any1: Any? = star[0]

    val a: Function<*, String>
    val b: Function<String, *>
    val c: Function<*, *>

    add(1)
//    add("") //not allow
}

//泛型类
private class List<T>(vararg elements: T) : Iterable<T> {

    var elementsArray = mutableListOf(*elements)

    override fun iterator(): Iterator<T> {
        return elementsArray.iterator()
    }


    operator fun get(index: Int): T = elementsArray[index]

    operator fun set(index: Int, value: T) {
        elementsArray.set(index, value)
    }

    val size: Int = elementsArray.size
}


// 泛型方法 printList
private fun <E> printList(inputList: List<E>) {
    for (element in inputList) {
        println("$element ")
    }
    println()
}

//声明处型变

//生产者
private interface Warp<out W> {
    val w: W

    fun getWarp(): W

//    val arrayW: Array<W> //not allow
}

private fun toWrap(warp: Warp<String>): Unit {
    val objects: Warp<Any> = warp // 这个没问题，因为 T 是一个 out-参数
}

private abstract class Comparable<in T> {
    abstract fun compareTo(other: T): Int
}

private fun compareTo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

private fun compareTo2(x: Comparable<*>) {
//    x.compareTo(1.0)
//    val y: Comparable<Double> = x
}


private interface Function<in T, out U>

private fun <T : Number> add(t: T) {
    // ……
}

fun <T> cloneWhenGreater(t: T)
        where T : Number,
              //              T : String, 只指定一个class ,接口可以多个
              T : kotlin.Comparable<T>,
              T : Cloneable {
}