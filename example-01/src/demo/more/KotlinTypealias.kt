package demo.more

import java.io.File

/**
 * Created by system on 2017/8/27.
 * 类型别名
 */
fun main(args: Array<String>) {
    val net: Net = Network()

    val enable = enable(net) {
        netStatus() == 0
    }
    println(enable)

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p)) // 输出 "[1]"
}


typealias Net = Network
typealias Node = Network.Node

typealias NodeSet = Set<Network.Node>
typealias FileTable<N> = MutableMap<N, MutableList<File>>

typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean

fun netStatus(): Int = 0

class Network {
    inner class Node
}

fun <T> enable(t: T, p: Predicate<T>): Boolean {
    return p(t)
}