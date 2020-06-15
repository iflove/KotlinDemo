package demo.chapter6

import utils.ln

/**
 * Created by system on 2017/8/26.
 * 函数
 */

private class KotlinFunction {

    fun run() {
        invoke("invoke")
        invokeWithNameParameter(method = "invokeWithNameParameter")
        invokeWithLambda(status = 1) { println("invokeWithLambda") }
        invokeWithLambda { println("invokeWithLambda") }

        FileSource().read(arrayOf('A'.toByte(), 'B'.toByte()))

        val b = intArrayOf(6, 8)
        // vararg 参数 类型是基本类型,即是 *Array 类型 否则 Array<out T>
        varargFun("1", 2, 4, *b, 10).ln()

        println(1 shl 2)

        val s = "infix" append " gc"
        println(s)
        this call ("append")

        memberFun()

    }

    fun invoke(method: String, invoke: Any = this) {
        println("call#method= $method $invoke")
    }

    fun invokeWithNameParameter(status: Int = 0, method: String, invoke: Any = this) {
        println("call#method= $method $invoke")
    }

    fun invokeWithLambda(status: Int = 0, method: String = "invokeWithLambda", invoke: Any = this, apply: () -> Unit) {
        println("call#method= $method $invoke")
    }

    abstract class Source {
        abstract fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size)
    }

    class FileSource : Source() {
        override fun read(b: Array<Byte>, off: Int, len: Int) {
            println("b.length = ${b.size} off = $off len = $len")
        }
    }

    //可变数量的参数
    fun varargFun(method: String = "varargFun", vararg s: Int) {
        s.forEach { print(it) }
    }

    //返回 Unit 的函数
    fun printHello(name: String?): Unit {
        if (name != null)
            println("Hello ${name}")
        else
            println("Hi there!")
        // `return Unit` 或者 `return` 是可选的
    }

    //单表达式函数
    fun double(x: Int) = x * 2


    //成员函数
    fun memberFun() {
        val visited = ""
        fun partialFun() {    //局部函数
            println(visited)
        }
        partialFun()
    }

    //泛型函数
    fun <T> singletonList(item: T): List<T> {
        return listOf(item)
    }

    infix fun String.append(s: String): String {
        return "$this$s"
    }

    infix fun call(method: String) {
        println("call#method= $method")
    }
}

fun main(args: Array<String>) {
    KotlinFunction().run()
}