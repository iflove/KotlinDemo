package demo.chapter3

import str

/**
 * Created by system on 2017/8/13.
 * 基本类型
 */

fun main(args: Array<String>) {

    println(str)

    //NOTE: Char 不是Kotlin的数字 (与Java 不同)
    val c: Char = 'c'
    val i: Int = c.toInt()
    println(c) // 'c'
    println(i) // 99

    123 //十进制
    0b00001011 //十进制
    0x0F //十进制
    1L //Long
    1.1f //Float
    123.5 //Long
//    支持数字字面值中的下划线（自 kotlin1.1 起）
    val oneMillion = 1_000_000

    //Numbers基本类型
    /*
    Type	Bit width
    Double	64
    Float	32
    Long	64
    Int	32
    Short	16
    Byte	8
    */
    val anDouble: Double = 123.4
    val anFloat: Float = 123.5f
    val anLong: Long = 123
    val anInt: Int = 123
    val anShort: Short = 123
    val anByte: Byte = 123


    val anDoubleBox: Double? = 123.4
    val anFloatBox: Float? = 123.5f
    val anLongBox: Long? = 123L
    val anIntBox: Int? = 123
    val anShortBox: Short? = 123
    val anByteBox: Byte? = 123

    //字符16-bit Unicode character
    val anChar: Char = 'A'
    val anCharBox: Char? = 'A'

    val low = -127
    val high = 127
    val noInIntegerCache = 128

    var boxedA: Int? = low
    var anotherBoxedA: Int? = low
    println(boxedA == anotherBoxedA) //true
    println(boxedA === anotherBoxedA) //true
    boxedA = high
    anotherBoxedA = high
    println(boxedA == anotherBoxedA) //true
    println(boxedA === anotherBoxedA) //true
    boxedA = noInIntegerCache
    anotherBoxedA = noInIntegerCache
    println(boxedA == anotherBoxedA) //true
    println(boxedA === anotherBoxedA) //false

    println(boxedA.hashCode())
    println(anotherBoxedA.hashCode())
    //
    val anIntegerA: Int? = 123 //对应 java.lang.Integer
    val anIntegerB: Int? = 123 //对应 java.lang.Integer
    println(anIntegerA === anIntegerB) //true

    anIntegerA as Number

    println(anIntegerA?.javaClass) //int
    println(anIntegerA.javaClass) //java.lang.Integer
    val anIntegerArray: Array<Int> = arrayOf(1, 2, 3)
    val anIntegerList: List<Int> = listOf(1, 2, 3)
    println(anIntegerArray.toString())
    println(anIntegerList.toString())
    println((anIntegerList[0] as Number).javaClass) //

    val anNewIntA: Int = anIntegerA //Start cast to kotlin.Int
    val anNewIntB: Int = anIntegerB as Int //
    val anNewIntC: Int = anIntegerB //Start cast to kotlin.Int

    //显式转换

//    val a: Byte = 1 // OK, 字面值是静态检测的
//    val b: Int = a // 错误

    //缺乏隐式类型转换并不显著，因为类型会从上下文推断出来，而算术运算会有重载做适当转换，例如：
    val l = 1L + 3 // Long + Int => Long

    println()

    val s = "Hello, world!\n" //字符串字面值
    //字符串
    for (c in s) {
        print(c)
    }
    //原生字符串 使用三个引号（"""）分界符括起来
    val text = """
    for (c in s) {
        print(c)
    }
    """
    println(text)
    //字符串模板
    val str = "$s.length is ${s.length - 1}" //
    println(str)

    val price = "${'$'}9.99"
    println(price)

    println()


}

