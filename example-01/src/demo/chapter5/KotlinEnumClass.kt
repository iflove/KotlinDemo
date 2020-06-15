package demo.chapter5

import utils.component1
import utils.component2


/**
 * Created by system on 2017/8/25.
 * 枚举类
 */


fun main(args: Array<String>) {
    for (it in KotlinEnumClass.Direction.values()) {
        println(it)
    }
    //必须与声明枚举类型名称一致, 否则抛出 IllegalArgumentException 异常。
    val north = KotlinEnumClass.Direction.valueOf("NORTH")
    println(north === KotlinEnumClass.Direction.NORTH)

    //枚举常量都具有在枚举类声明中获取其名称和位置的属性
    val (name, ordinal) = KotlinEnumClass.Direction.EAST
    println("$name $ordinal")


    KotlinEnumClass().printAllValues<KotlinEnumClass.ProtocolState>()
    println()
    KotlinEnumClass().printValue<KotlinEnumClass.ProtocolState>("WAITING")
}


private class KotlinEnumClass {
    //类型安全的枚举
    enum class Direction {
        NORTH, SOUTH, WEST, EAST;
    }

    //枚举都是枚举类的实例,可以初始化
    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    //枚举常量也可以声明自己的匿名类
    enum class ProtocolState {
        WAITING {
            override fun signal() = TALKING
        },

        TALKING {
            override fun signal() = WAITING
        };

        abstract fun signal(): ProtocolState
    }

    //列出定义的枚举常量
    inline fun <reified T : Enum<T>> printAllValues() {
        print(enumValues<T>().joinToString { it.name })
    }

    //通过名称获取枚举常量
    inline fun <reified T : Enum<T>> printValue(name: String) {
        print(enumValueOf<T>(name))
    }

}

