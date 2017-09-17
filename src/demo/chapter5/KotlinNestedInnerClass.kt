package demo.chapter5

/**
 * Created by system on 2017/8/24.
 * 嵌套类与内部类
 */
fun main(args: Array<String>) {
    println(KotlinNestedInnerClass.KotlinNestedClass().bra())
    println(KotlinNestedInnerClass().KotlinInnerClass().bra())
    println(KotlinNestedInnerClass().KotlinInnerClass().reference())
}

private class KotlinNestedInnerClass {
    private val bra: String = "C"

    class KotlinNestedClass {
        fun bra() = KotlinNestedInnerClass().bra
    }

    //内部类 标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用
    inner class KotlinInnerClass {
        fun bra() = bra
        fun reference() = this@KotlinNestedInnerClass  //This 表达式
    }

    //匿名内部类 @see KotlinObject.kt
}

