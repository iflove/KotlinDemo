package demo.chapter5

/**
 * Created by system on 2017/8/25.
 * 扩展（很强大）
 */

fun main(args: Array<String>) {
    val kotlinExtension = KotlinExtension()
    kotlinExtension.extensionFun()
    println(kotlinExtension.fileName)
    println(kotlinExtension.fileName())
    kotlinExtension.member()
    val kotlinExtension2: KotlinExtension? = null
    kotlinExtension2.toString()
    println(kotlinExtension.nullableReceiver())
    println(kotlinExtension2.nullableReceiver())

    println(KotlinExtension.anProperty)
    KotlinExtension.extensionFun()

    val localBookmark = LocalBookmark()
    val cloudBookmark = CloudBookmark()

    LocalBookmarkManage().syncLocal(localBookmark) //输出 syncToCloud
    CloudBookmarkManage().syncLocal(cloudBookmark) //输出 syncFromLocal —— 分发接收者虚拟解析

    LocalBookmarkManage().syncLocal(cloudBookmark)//输出 syncToCloud —— 扩展接收者静态解析
    CloudBookmarkManage().syncLocal(localBookmark)//输出 syncFromLocal —— 分发接收者虚拟解析

}

class KotlinExtension {
    //成员函数比扩展函数优先
    fun member() {
        println("call#member")
    }

    fun fileName(): String {
        return "KotlinExtension.class"
    }

    companion object
}

class KotlinInteriorExtension {
    fun start() {
        println("call#start")
    }

    fun KotlinExtension.stop() {
        start()
        member() //扩展声明为成员时 扩展函数优先
        this@KotlinInteriorExtension.member() //使用 限定this
    }

    fun member() {
        println("call#member")
    }
}

//扩展的对象类型 KotlinExtension
fun KotlinExtension.extensionFun() {
    println("this@${this} call#extensionFun") //
}

fun KotlinExtension.member() {
    println("call#extension") //
}

//接收者类型表达式中使用泛型 要在函数名前声明泛型参数
fun <E> List<E>.addAll() {
    //...
}

//扩展属性(Extension Property)  实际扩展get* 函数而已
val KotlinExtension.fileName
    get() = "KotlinExtension.kt"


//可为空的接收者(Nullable Receiver)
fun KotlinExtension?.nullableReceiver(): String {
    return if (this == null) "null Receiver" else "not null Receiver"
}


//对同伴对象(Companion Object)的扩展
val KotlinExtension.Companion.anProperty: Int get() = 1

fun KotlinExtension.Companion.extensionFun() {
    println("call#Companion.extensionFun")
}

//扩展是静态解析的
open class LocalBookmark

class CloudBookmark : LocalBookmark()

open class LocalBookmarkManage {

    open fun LocalBookmark.sync() {
        println("syncToCloud")
    }

    open fun CloudBookmark.sync() {
        println("syncFromCloud")
    }

    fun syncLocal(localBookmark: LocalBookmark) {
        localBookmark.sync()
    }
}

class CloudBookmarkManage : LocalBookmarkManage() {

    override fun LocalBookmark.sync() {
        println("syncFromLocal")
    }

    override fun CloudBookmark.sync() {
        println("syncToLocal")
    }

}


