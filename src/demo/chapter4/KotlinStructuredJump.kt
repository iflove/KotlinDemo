package demo.chapter4

/**
 * Created by system on 2017/8/15.
 * 返回和跳转
 */

fun main(args: Array<String>) {
//    结构化跳转表达式
    out@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) break@out;print("[$i , $j] ")
        }
    }
    println()
    out@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue@out;print("[$i , $j] ")
        }
    }
    println()

    var nullInt: Int? = 1
//    nullInt = null
    nullInt = nullInt?.toInt() ?: throw IllegalArgumentException("Oh")

    fun performLabelReturn() {
        val array = arrayOf(1, 2, 3)
        array.forEach {
            if (it == 2) return  //
            println(it)
        }
        println("performLabelReturn can't reach")
    }
    performLabelReturn()
    println()

    fun performLabelLambdaLimitReturn() {
        val array = arrayOf(1, 2, 3)
        array.forEach {
            if (it == 2) return@forEach //
            println(it)
        }
        println("performLabelLambdaLimitReturn can reach")

    }
    performLabelLambdaLimitReturn()
    println()

    fun performLabelLimitReturn() {
        val array = arrayOf(1, 2, 3)
        array.forEach limit@ {
            if (it == 2) return@limit //
            println(it)
        }
        println("performLabelLimitReturn can reach")
    }
    performLabelLimitReturn()
    println()

    fun performLabelAnonymousLambdaLimitReturn() {
        val array = arrayOf(1, 2, 3)
        array.forEach(fun(value: Int) {
            if (value == 2) return  // local return to the caller of the anonymous fun, i.e. the forEach loop
            println(value)
        })
        println("performLabelAnonymousLambdaLimitReturn can reach")

    }
    performLabelAnonymousLambdaLimitReturn()
    println()


    fun a(): Int {
        return@a 12
    }

    println(a())

}