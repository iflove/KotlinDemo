package demo.chapter9

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


/**
 * Created by system on 2017/8/27.
 */
suspend fun main() = coroutineScope {
    for (i in 0 until 10) {
        launch {
            delay(1000L - i * 10)
            print("❤️$i ")
        }
    }
}

//fun main() {
//    KotlinCoroutines().test()
//}

private class KotlinCoroutines {

    //挂起函数
    suspend fun suspend() {
        println("挂起")
    }

    suspend fun test() = coroutineScope {
        launch {
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
        val one = async() { doSomethingUsefulOne() }
        val two = async() { doSomethingUsefulTwo() }
        println("Completing...")
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}