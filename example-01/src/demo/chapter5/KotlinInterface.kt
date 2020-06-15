package demo.chapter5

/**
 * Created by system on 2017/8/22.
 * 接口
 */
fun main(args: Array<String>) {
    val kotlinLanguage = KotlinLanguage()
    println(kotlinLanguage.language)
    println(kotlinLanguage.that)
    println(kotlinLanguage.that === kotlinLanguage)
    kotlinLanguage.onReady()
    kotlinLanguage.onUpgrade()

    MultipurposePrinter().print()
}

private interface KotlinInterface {
    val language get() = "Kotlin"
    val that: KotlinInterface

    fun onUpgrade() {
        println("call#onUpgrade")
    }

    fun onReady() //

}

private class KotlinLanguage : KotlinInterface {
    override val that: KotlinInterface
        get() = this

    override fun onReady() {
        println("call#onReady")
    }

}

private interface Printer {
    fun print()
}

private interface ColorPrinter : Printer {
    override fun print() {
        println("ColorPrinter#print")
    }

//    val printerType get() = "ColorPrinter"
}


private interface BlackPrinter : Printer {
    override fun print() {
        println("BlackPrinter#print")
    }

    val printerType get() = "BlackPrinter"
}

private class MultipurposePrinter : ColorPrinter, BlackPrinter {

    override fun print() {
        println("MultipurposePrinter#print")
        super<BlackPrinter>.print()
        super<ColorPrinter>.print()

        super.printerType
    }
}