package demo.chapter8

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis


/**
 * Created by system on 2017/8/27.
 */

//fun main(args: Array<String>) {
//
//    KotlinCoroutines().test()
//}

private class KotlinCoroutines {

    //挂起函数
    suspend fun suspend() {
        println("挂起")
    }

    fun test() {
        launch(CommonPool) {
            // create new coroutine in common thread pool
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main function continues while coroutine is delayed
        Thread.sleep(2000L) // block




    }



}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

fun main(args: Array<String>) = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async(CommonPool) { doSomethingUsefulOne() }
        val two = async(CommonPool) { doSomethingUsefulTwo() }
        println("Completing...")
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}