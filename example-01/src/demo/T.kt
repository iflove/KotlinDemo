package demo

import java.lang.ref.WeakReference

/**
 * Created by system on 2017/12/27.
 */


fun main(args: Array<String>) {
    for (index in 1..3) {
        Ref().testWeakReference()
    }
}

class Ref {
    val b = ByteArray(1024 * 1024)
    fun testWeakReference() {
        val weakReference = this@Ref.weakReference()
        Thread({
            Thread.sleep(300)
            println(weakReference()?.b)
        }).start()
    }
}


class KWeakReference<T> internal constructor(any: T) {
    private val weakRef = WeakReference<T>(any)

    operator fun invoke(): T? {
        return weakRef.get()
    }
}

internal fun <T> T.weakReference() = KWeakReference(this)