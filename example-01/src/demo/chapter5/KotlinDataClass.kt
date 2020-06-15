@file:kotlin.jvm.JvmName("KotlinDataClassKt")

package demo.chapter5

/**
 * Created by system on 2017/8/22.
 * 数据类
 */
fun main(args: Array<String>) {
    val user1 = KotlinDataClass.User("小明", 19)
    val user2 = KotlinDataClass.User("小明", 19)
    println(user1 == user2)
    println(user1)
    val copyXiaoMing = user1.copy(age = 20)
    println(copyXiaoMing)
    println(user1.component1())
    val bb = KotlinDataClass.User("bb")
    println(bb)

    //数据类和解构声明
    val (name, age) = KotlinDataClass.User("Lisa", 18)
    println("$name, $age years of age")

    //标准数据类
    val anPair: Pair<Char, Char> = Pair('A', 'B')
    println("first = ${anPair.first}, second = ${anPair.second}")
    val (a, b, c) = Triple('A', 'B', 'C')
    println("($a, $b, $c)")

    val pair = "1" to "2"
}

private class KotlinDataClass {

    open class Person

    //数据类本身是 final,必须有主构造器,至少一个参数
    data class User(val name: String, val age: Int = 0) : Person() {

        //编译器会根据主构造函数的参数生成以下函数,根据需求 override

//    override fun equals(other: Any?): Boolean {
//        return super.equals(other)
//    }
//
//    override fun hashCode(): Int {
//        return super.hashCode()
//    }
//
//    override fun toString(): String {
//        return super.toString()
//    }

//    Error: Conflicting overloads:
//    fun component1(){
//
//    }

    }
}
