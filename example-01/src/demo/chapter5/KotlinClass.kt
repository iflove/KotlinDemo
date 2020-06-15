package demo.chapter5

/**
 * Created by system on 2017/8/16.
 * 类与对象
 */
fun main(args: Array<String>) {
    println(EmptyClass() is Any)

    val father = Father()
    val son = Son()
    println(father is Son)
    println(son is Father)

    val fatherSon: Father = Son()
    println(fatherSon is Son)
    println(fatherSon is Father)

    val sonFatherSon: Son = fatherSon as Son
    println(sonFatherSon != null)

    val newFather: Son? = father as? Son
    val newFather1 = father as? Son  //newFather1 start define val newFather : Son?
//    val newFather2 = father as Son // newFather1 start define val newFather : Son  occur error
    println(newFather == null)

    Person("小明", 1)
    Person("小红", 1, 10)
}

//可见性修饰符在类中使用情况

open class Father {
    private val name = "哔哔" //private can't open
    protected open val bloodType = "AB"
    internal val number = 1000
    open val age = 28

    protected class Nested {
        val body = {}
        private val cipher = null

        private fun print() {
            //can't access private
//            println(name)
//            println(bloodType)
//            println(number)
//            println(age)

            body

        }
    }

    open fun print() {
        println(name) //can't access private
        println(bloodType)
        println(number)
        println(age)

        Nested().body

//        Nested().cipher//Kotlin 中外部类不能访问内部类的 private 成员

    }

}

class Son : Father() {
    override final val bloodType: String = "O" //protected // final Redundant
//    override public val bloodType: String = "O" // 能覆盖

    override val age: Int = 10 // public

    override open fun print() { //Warning: 'open' has no effect in a final class
//        println(name) //can't access private
        println(bloodType)
        println(number)
        println(age)

        Nested().body
    }

}

open class BigSon : Father() {
    override final val bloodType: String = "AB"  //can use final
}


//类与对象
class EmptyClass

//Redundant

//Constructors
open class Person /*private*/ constructor(firstName: String) {
    class A //empty class 下面接着是次构造函数 ，Error: Expecting member declaration, 期待成员声明

    val money = 1000_000

    init {
        println("init block: firstName= $firstName")
        println("init block: money= $money")
    }

    //次构造函数
    constructor(firstName: String, age: Int) : this(firstName) {
        println("secondary constructor: firstName= $firstName")
        println("secondary constructor: age= $age")
        println("init block: money= $money")
    }

    constructor (firstName: String, age: Int, money: Int) : this(firstName, age) {
        println("secondary constructor: firstName= $firstName")
        println("secondary constructor: age= $age")
        println("init block: money= $money")
    }

}

class Student(firstName: String) : Person(firstName) {
}

open class Human {
    constructor(name: String) {

    }

    constructor(name: String, age: Int) {

    }
}

class Woman : Human {
    constructor(name: String) : super(name)
    constructor(name: String, age: Int) : super(name, age)
}

//允许通过主构造函数覆盖次构造函数
class Man(name: String) : Human(name)


open class Thread {
    open fun run() {
        println("Thread#run")
    }

    fun start() {
        println("Thread#start")
    }
}

interface Runnable {
    fun run() {
        println("Thread#run")
    } // 接口成员默认就是“open”的


}

class HandlerThread() : Runnable, Thread() {
    //编译器要求覆盖 run():
    override fun run() {
        super<Thread>.run() // 调用 Thread.run()
        super<Runnable>.run() // 调用 Runnable.run()
    }
}

abstract class AbstractClass { //open 多余的,因为抽象类终究是父类,所以更不能用final 修饰
    open fun doSomething() {
    }

    abstract fun fly() //子类必须 override
}

class AbstractClassImpl : AbstractClass() {
    override fun fly() {
    }

    override fun doSomething() {//override 开放成员
        super.doSomething()
    }
}

