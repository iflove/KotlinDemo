package demo.chapter5

import java.util.*

/**
 * Created by system on 2017/8/21.
 * object(对象声明)
 */
fun main(args: Array<String>) {

    val point = object /*: Any()*/ { //默认继承 Any
        var x: Int = 0 //必须进行初始化
        var y: Int = 0

        override fun toString(): String {
            return "point[$x，$y]"
        }
    }
    point.x = 100
    point.y = 300
    println(point)

    val singleton = Singleton
    val singleton1 = Singleton
    println(singleton === singleton1)


    KotlinObject().run()

    World.sayHello()
    World.sayHello()
    World().sayHello()

    val run  = Runnable {  }
}

//对象声明
object Singleton { //决不能声明局部作用域(函数中)

}

class KotlinObject {

    private fun privateObject() = object { //返回: <anonymous object : Any>
        val name = "123"
    }

    fun publicObject() = object { // 返回Any 建议private
        val name = "ABC"
    }

    fun run() {
        println(privateObject().name)
        //println(publicObject().name) //错误：未能解析的引用“name”
        var visible = true
        call(object : CallBack {
            override fun call() {
                visible //对象表达式中的代码可以访问来自包含它的作用域的变量
                println("Anonymous#call@${this.hashCode()}")
            }

        })
        call(object : CallBack {
            override fun call() {
                visible //对象表达式中的代码可以访问来自包含它的作用域的变量
                println("Anonymous#call@${this.hashCode()}")
            }

        })
        call(OneCallBack)
        call(OneCallBack)

    }

    object OneCallBack : CallBack {
        //因为对象表达式不能绑定名字,这称为对象声明
        override fun call() {
            println("OneCallBack#call@${this.hashCode()}")
        }
    }

    fun call(call: CallBack) {
        call.call()
    }

    interface CallBack {
        fun call(): Unit
    }
}


open class World {

    //Companion 是companion object 默认名字可省略,仅且有一个伴生对象
    companion object Companion : Observer {
        @JvmField //@JvmField 标注这样的属性使其成为与属性本身具有相同可见性的静态字段。
        val time = System.nanoTime()

        const val VERSION = "1.1.4.2" //kotlin 常量(const 标注的（在类中以及在顶层的）属性), 在 Java 中会成为静态字段：

        override fun update(o: Observable?, arg: Any?) {
        }


        //        @JvmStatic  //打开注释编译报错,存在相同的函数声明, 这充分地证明了伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员
        fun sayHello() {
            println("sayHello@${this.hashCode()} ")
        }
    }

    fun sayHello() {
        println("sayHello@${this.hashCode()} ")
    }

}
