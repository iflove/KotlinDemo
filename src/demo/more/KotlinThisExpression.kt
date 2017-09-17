package demo.more

/**
 * Created by system on 2017/8/24.
 * This 表达式
 */
fun main(args: Array<String>) {
    val kotlinThisExpression = KotlinThisExpression()
    println(kotlinThisExpression.leftReference() === kotlinThisExpression)
    kotlinThisExpression.InnerKotlinThisExpression().test()
}

private class KotlinThisExpression {
    val thisClassObject get() = this

    inner class KotlinThisExpression {
        //val thisClassObject get() = this@KotlinThisExpression //不明确label
        val thisClassObject get() = this //内部类名相同,不能用限定的 this

    }


    inner class InnerKotlinThisExpression { // 隐式标签 @InnerKotlinThisExpression
        fun InnerKotlinThisExpression.fuck() { // 隐式标签 @fuck
            val a = this@KotlinThisExpression // KotlinThisExpression 的 this
            val b = this@InnerKotlinThisExpression // InnerKotlinThisExpression 的 this

            val c = this // fuck() 的接收者，一个 InnerKotlinThisExpression
            val d = this@fuck // fuck() 的接收者，一个 InnerKotlinThisExpression

            val label = label@ fun String.() {
                println(this)// label 的接收者
            }

            "label".label()
            val lambda = { ->
                // fuck() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                println(this)
            }

            lambda()
        }

        fun test() {
            fuck()
        }
    }
}

private fun KotlinThisExpression.leftReference() = this.thisClassObject //this 表示在点左侧传递的 接收者 参数。
