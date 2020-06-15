package demo.chapter4

import str


/**
 * Created by system on 2017/6/7.
 * 变量和常量
 */

fun main(args: Array<String>) {
    println(str)
    //变量
    val s = "Example" // A Immutable String
    var v: String = "Example" // A Mutable String
    println(s === "Example")


    val aImmutableIntVariable = 0x001 //aImmutableIntVariable 类型为 Int

//    var aMutableIntVariable: Int = "0x002" //语法error
    var bMutableIntVariable: Int = 0x002

    var cMutableVariable: Any //显式确定变量类型,必须要接收该类型的初始化。

//    aImmutableIntVariable = 1 //不能重新分配 Val cannot be reassigned
//    bMutableIntVariable = ""//一旦类型确定,只能接受该类型的值


//    aNullNothing = 1 //Nothing error
//    cNullUnable = null

    cNullable = 1
    dNullable = null //可以 null

    cNullUnable.dec() //保证不会导致 NPE
    val hc = dNullable?.hashCode() //dNullable == null return null, hc is null
    val dec = cNullable?.dec() // cNullable !=null return cNullable.dec(),dec is "0"

    cNullable!!.dec() // cNullable !=null execute dec()
    dNullable!!.toString() // dNullable == null throws NPE
    var aNotNullObject = cNullable!!


    //类型检测和安全的类型转换
    val obj: Any = ""
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) { // 与 !(obj is String) 相同
        print("Not a String")
    } else if (obj is String) {
        print(obj.length)
    } else {
        print(obj.length)
    }

    when(obj){
        is String -> obj.length
    }

    println()

}


//常量 （编译期）
/*
已知值的属性可以使用  const 修饰符标记为 编译期常量必须满足以下需求
# 位于顶层或者是 object 的一个成员
# String 或原生类型 值初始化
# 没有自定义 getter
*/
const val CONST_VAL = 1
//const val CONST_VAL_GET get() = 1 //Error: Const 'val' should not have a getter
//const val CONST_VAL_TEST :Any = 1  //error

fun testConstInFunction() {
//    const val CONST_VAL = 1 //error
}

object Kotlin {
    const val CONST_VAL: String = "object 常量"
    @JvmStatic
    fun main(args: Array<String>) {

    }
}

//类型安全和智能转换
var aNullNothing = null

//var bNullUnable: Int = null //不能为空
var cNullUnable = 1 //不能为空

var cNullable: Int? = null //能为空
var dNullable: Any? = 1 //能为空
val cReadNullable: Int? = 1000 //能为空

//检查 null
//open class TestCheckNull {
//    val cReadNullable: Int? = 1
//    val cGetReadNullable: Int? get() = 1
//    open val cOverrideReadNullable: Int? = 1
//
//    fun fun0(): Unit {
//        if (cReadNullable != null) {
//            cReadNullable.dec() //tips replace safe access expression
//        }
//        if (cGetReadNullable != null) {
//            cGetReadNullable.dec()
//        }
//        if (cOverrideReadNullable != null) {
//            cOverrideReadNullable.dec()
//        }
//    }
//
//}
//

