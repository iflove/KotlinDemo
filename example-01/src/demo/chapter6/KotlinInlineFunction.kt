package demo.chapter6

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by system on 2017/9/10.
 * 内联函数
 */

fun main(args: Array<String>) {
    KotlinInlineFunction().test()
    KotlinInlineFunction().findType(1)
    KotlinInlineFunction().findReifiedType(1L)
//    println(membersOf<Int>().joinToString ("\n"))
}

internal inline fun <reified T : Number> membersOf() = T::class.constructors

private class KotlinInlineFunction {

    val max inline get() = Int.MAX_VALUE
    inline val max1 get() = Int.MAX_VALUE
    inline val max2 inline get() = Int.MAX_VALUE //编译也ok 。。。

    //Inline property cannot have backing field
    var count = 0
    var counter
        inline get() = count //set/get 其中一个标注为inline, 都不能使用 backing field
        inline set(value) {
            count = value
        }
    //
    inline var doubleCounter
        get() = count * 2 //set/get 其中一个标注为inline, 都不能使用 backing field
        set(value) {
            count *= value
        }

    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    inline fun <T> lockInline(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    inline fun test() { //warn 内联函数最适用于具有lambda参数的函数
        var a = 1
//        lock(ReentrantLock()){}
//        lockInline(ReentrantLock()){}
        val milliseconds = System.currentTimeMillis()
        lock(ReentrantLock()) {
            //  return  // 不允许不带标签的return. return@lock
            a += 1
        }
        val milliseconds2 = System.currentTimeMillis()
        lockInline(ReentrantLock()) {
            return
            a += 1
        }
        val milliseconds3 = System.currentTimeMillis()
        println(a)
        println("no-inline: ${milliseconds2 - milliseconds}")
        println("inline: ${milliseconds3 - milliseconds2}")


//        fun test1(){}  //内联函数不支持局部函数

    }

    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
        // ……
    }

    inline fun foo(noinline notInlined: () -> Unit) {
        // …… 仅一个参数又用 noinline 修饰, inline 将无效
    }

    fun hasZeros(ints: List<Int>): Boolean {
        ints.forEach {
            if (it == 0) return true // 从 hasZeros 返回
        }
        return false
    }

    //
    inline fun post(crossinline body: () -> Unit) {
        Runnable { body() }
    }

    fun <T> findType(t: T) {
        //因为T 不是静态已知的 Kotlin 类的引用，所以不能 T::class
        println((t as Any)::class)
    }

    //内联函数支持具体化的类型参数,不需要反射，正常的操作符如 !is 和 as 能正常使用
    inline fun <reified T : Number> findReifiedType(t: T) {
        println(T::class)
        println(Int.MIN_VALUE is T)
        println(membersOf<Int>().joinToString("\n"))
    }

    inline fun <reified T : Number> membersOf() = T::class.constructors

}


//公有 API 内联函数限制使用private 与 internal 声明以及其部件 （顶层声明）
//inline fun publishApi(body: () -> Unit) {
//    privateFun()
//    internalFun()
//}
//
//@PublishedApi //检查其函数体加以限制
//internal inline fun internalApi(body: () -> Unit) {
//    privateFun()
//    internalFun()
//}
//
//private fun privateFun(): Unit {
//
//}
//
//internal fun internalFun(): Unit {
//
//}