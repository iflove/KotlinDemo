package demo.more

/**
 * Created by system on 2017/8/24.
 * 解构声明
 */
fun main(args: Array<String>) {
    val (name, age) = KotlinDeconstruction.Person("jack", 32)
    println("$name $age")

    val request = KotlinDeconstruction.request()
    val (rs, code) = request
    println("result = $rs , code = $code")
    //下划线用于未使用的变量
    val (_, responseCode) = request
    println(responseCode)
    println(request.component1())
    println(request.component2())

    //解构声明和Map
    val map = mutableMapOf<String, String>()
    for (it in 1..10) {
        map.put(it.toString(), it.toString())
    }
    for ((k, v) in map) {
        println("map key = $k, value = $v")
    }
    map.mapValues { entry -> println("key = ${entry.key}, value = ${entry.value}!") }
    map.mapValues { (key, value) -> println("key = $key, value = $value!") }
}


private class KotlinDeconstruction {
    class Person(val name: String, val age: Int) {
        operator fun component1(): Any = name
        operator fun component2(): Any = age
    }

    data class Response(val result: String, val code: Int)

    companion object {
        fun request(): Response {
            //request network
            return Response("ok", 200)
        }
    }
}
