package demo.chapter7

import jsource.chapter7.JavaDataType

/**
 * Created by system on 2017/9/16.
 */

val className = "KolinCallJavaKt"

fun main(args: Array<String>) {


    val nullAny = JavaDataType.nullObj //实际: val nullAny: Any!
    val safeNullAny: Any? = JavaDataType.nullObj

    println(safeNullAny?.hashCode())
    println(nullAny?.hashCode()) //null check

//    val notNullAny: Any = JavaDataType.nullObj //赋值时 NPE
//    nullAny.hashCode() //使用时 NPE


    val name = JavaDataType.NAME
    println(name.javaClass)

    val aByte: Byte? = JavaDataType().aByte
    println(aByte!!.javaClass)
    val message: Int? = JavaDataType().anInt
    println(message)
//    val character = Character

    if (Character.isLetter('A')) {
        // ……
    }

//    JavaDataType.load()

    for (s in JavaDataType.IDES) {
        println(s)
    }
    println(JavaDataType.IDES[0])
}


class MyThread : Thread(), Runnable {
    override fun run() {

    }
}
