package demo.chapter6

import utils.DOSOMETHING
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by system on 2017/8/26.
 * 高阶函数
 */
fun main(args: Array<String>) {
    KotlinHigherOrderFunctions().test()
}

private class KotlinHigherOrderFunctions {
    //函数用作参数 () -> Unit 不带参数并 且返回 Unit 类型值的函数
    fun post(runnable: () -> Unit) {
        println("post before")
        runnable()
        println("post after")
    }

    fun postDelay(delay: Int, runnable: () -> Unit) {
        println("postDelay before")
        runnable()
        println("postDelay after")
    }

    fun <T> send(message: (body: Any) -> T): Boolean {
        println("send before")
        println(message(""))
        println("send after")
        return true
    }

    fun sendMore(message: (Any, Boolean) -> Message): Boolean {
        println("sendSound before")
        println(message("", false))
        println("sendSound after")
        return true
    }

    fun request() {
        println("#request")
    }

    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    fun sayHello(name: String) = "Hello $name"

    fun sayHi() {
        println("Hi")
    }

    val result = lock(ReentrantLock(), this::request)


    fun test() {
        post(this::sayHi) //函数引用
        post { println("post") } //Lambda
        postDelay(1000) { println("postDelay") }


        val sendSuccess = send { sayHello("Lisa") }
        println(sendSuccess)

        sendMore { body, isSound ->
            Message(true, sayHello("Lisa"))
        }


        val lock = lock(ReentrantLock()) { "ReentrantLock" }
        println(lock)


        println({}) //输出: () -> kotlin.Unit
        println({ "String" })//输出: () -> kotlin.String
        val string = { "String" }
        println(string())//输出: String

        //显式
        fun explicitAnonymous(): () -> Int {
            return { -> 1 } //没参数不能有括号()
        }

        println(explicitAnonymous()())

        val sum = { x: Int, y: Int -> x + y }  //val sum: (Int, Int) → Int
        val sum2: (Int, Int) -> Int = { x, y -> x + y } //val sum2: (Int, Int) → Int
        fun sum3(sum: (Int, Int) -> Int) {
            println(sum(0, 0))
        }

        fun sum4(sum: (a: Int, b: Int) -> Int) {
            println(sum)
        }
        sum3 { a, b -> 1 + 3 }
        println(sum(1, 2))

        fun <T> filter(predicate: (T) -> Boolean) {
            DOSOMETHING()
        }

        filter<Int>() { it > 0 } //() 可略
        filter<Int> { it > 0 }
        filter<Int> { _ -> false }
        filter<Int> {
            val shouldFilter = it > 0
            return@filter shouldFilter
        }

        //匿名函数
        val sumAnonymous = fun(x: Int, y: Int) = x + y //返回类型可以自动推断
        println(sumAnonymous(1, 3))
        val sumAnonymous2 = fun(x: Int, y: Int): Int {
            return x + y
        }
        filter<Int>(fun(item) = item > 0) //推断出的参数类型可以省略. 只能在括号内传递

        //闭包
        var aNumber = 0
        run {
            aNumber += 1
        }
        val add = fun() {
            aNumber += 1
        }
        add()
        println("aNumber: $aNumber")

//        带接收者的函数字面值
        val sumR = fun Int.(other: Int): Int = this + other //val sumR: Int.(Int) → Int
        println(1.sumR(2))
    }

    data class Message(val isSound: Boolean = false, val message: String)


}

