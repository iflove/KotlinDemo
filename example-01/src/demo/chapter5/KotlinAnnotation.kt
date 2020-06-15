@file:JvmName("KotlinAnnotationKt")

package demo.chapter5

import jsource.JavaAnnotation
import kotlin.reflect.KClass

/**
 * Created by system on 2017/8/27.
 * 注解
 */
@KotlinAnnotation.KotlinFileName("KotlinAnnotation.kt")
fun main(args: Array<String>) {

    val run = @KotlinAnnotation.Anonymous { println("run") }

    //java
    @JavaAnnotation.Describe("see")
    class See

    @JavaAnnotation.SinceJava(name = "jdk", version = 1_8_0)
    class JDK

    @JavaAnnotation.Targets(Any::class, String::class)
    class Targets

    @JavaAnnotation.Targets(*arrayOf(Any::class, String::class))
    class Targets2

    fun printId(intId: JavaAnnotation.IntId) {
        println(intId.value)
    }

    @JavaAnnotation.IntId(Int.MAX_VALUE)
    class Res

    printId(Res::class.annotations[0] as JavaAnnotation.IntId)

}


@KotlinAnnotation.ApplicationScope
object KotlinAnnotation {
    internal annotation class KotlinFileName constructor(val name: String)

    @Target(AnnotationTarget.CLASS,
            AnnotationTarget.FILE,
            AnnotationTarget.FUNCTION,
            AnnotationTarget.VALUE_PARAMETER,
            AnnotationTarget.PROPERTY_GETTER,
            AnnotationTarget.EXPRESSION)
    @Retention(AnnotationRetention.SOURCE)
    @MustBeDocumented
    annotation class ApplicationScope

    private annotation class FileScope constructor(@ApplicationScope val file: KotlinFileName, val funS: FunScope)

    private annotation class FunScope

    annotation class FieldScope

    annotation class Anonymous

    class User(@field:FieldScope val name: String, @get:[ApplicationScope FunScope] val age: Int)

    annotation class Targets(vararg val value: KClass<*>)
    annotation class TargetArrays(val value: Array<KClass<*>>)
}