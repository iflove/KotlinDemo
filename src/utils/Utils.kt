package utils

/**
 * Created by system on 2017/8/25.
 */

fun Any.ln() {
    println()
}

fun DOSOMETHING(): Unit = System.err.println("An operation is not implemented")



operator fun <E : Enum<E>> Enum<E>.component1() = this.name
operator fun <E : Enum<E>> Enum<E>.component2() = this.ordinal
