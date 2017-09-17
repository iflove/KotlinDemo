package demo.chapter5

/**
 * Created by system on 2017/8/24.
 * 密封类
 */
fun main(args: Array<String>) {
    val kotlinSealedClass = ChildrenKotlinSealedClass()
    println(eval(kotlinSealedClass))
}

sealed class KotlinSealedClass

class ChildrenKotlinSealedClass : KotlinSealedClass()

class GirlKotlinSealedClass : KotlinSealedClass()

private fun eval(k: KotlinSealedClass): String = when (k) {
    is ChildrenKotlinSealedClass -> "ChildrenKotlinSealedClass"
    is GirlKotlinSealedClass -> "GirlKotlinSealedClass"
    //不再需要 else 分支 已经覆盖了所有的情况
}

