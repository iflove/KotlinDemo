package demo.chapter5

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by system on 2017/8/25.
 * 委托(Delegation)
 */

private class KotlinDelegation {

    interface Printer {
        fun print()
    }

    class ColorPrinter : Printer {
        override fun print() {
            println("ColorPrinter#print")
        }
    }

    class BlackPrinter : Printer {
        override fun print() {
            println("BlackPrinter#print")
        }
    }

    class MultipurposePrinter(val printer: Printer) : Printer by printer {
        //可覆盖 , 不覆盖转发printer print 方法
        override fun print() {
            printer.print()
            println("override#print")
        }
    }
}

private class DelegationProperties {
    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name} in $thisRef.'")
        }
    }

    class ReadDelegate : ReadOnlyProperty<Any?, String> {
        override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }
        //不需要 setValue
    }

    companion object {
        fun test() {
            var p: String  by Delegate()
            p = "default"
            p = "$p  \nchange"

            val read by ReadDelegate()
            println(read)

            //标准委托
            val lazyValue by lazy<String>(LazyThreadSafetyMode.SYNCHRONIZED) {
                println("computed!")
                "Hello" //同步锁的（synchronized）
            }
            println(lazyValue)
            println(lazyValue)

            //可观察属性 Observable
            var name by Delegates.observable("No Name") { prop, old, new ->
                println("被赋值的属性：${prop.name},  $old > $new")
            }
            name = "両儀式"
            name = "式"

            var skip by Delegates.vetoable("Null") { property, oldValue, newValue ->
                println("被赋值的属性：${property.name},  $oldValue > $newValue")
                false
            }
            skip = "Test"
            println(skip)

            //map
            val languageMap = mapOf("language" to "kotlin")
            val language by languageMap //变量名就是map的key 否则找不到该key Exception: NoSuchElementException
            println(language)

            val user = User(mapOf(
                    "name" to "John Doe",
                    "age" to 25
            ))
            println("${user.name} ${user.age}")

            letMake { ->
                println("init")
                User(mapOf("Twins" to 17))
            }

            MyActivity().draw()

        }

        //局部委托属性（自 1.1 起）
        fun letMake(take: () -> User) {
            val lazyUser by lazy(take)

            //todo change true
            if (false && lazyUser.enable()) {
                lazyUser.make()
            }

        }

        class User(map: Map<String, Any?>) {
            val name: String by map
            val age: Int     by map

            fun make() {
                println("make")
            }

            fun enable() = true
        }

        //资源类
        class R {
            object id {
                val textView = 0x003
                val imageView = 0x004
            }

            object string {
                val hello_world = 0x001
            }

            object drawable {
                val icon_launch = 0x002
            }
        }

        open class View(val id: Int)
        open class ImageView(id: Int) : View(id)
        open class TextView(id: Int, var text: String = "") : View(id)


        class MyActivity {

            val helloWorld by findResourceById<String>(R.string.hello_world)
            val textView by findResourceById<TextView>(R.id.textView)

            inline fun <reified T> findResourceById(id: Int): ResourceLoader<T> {
                return ResourceLoader<T>(id)
            }

            fun draw() {
                println(helloWorld)
                textView.text = "Hello"
                println(textView.text)
            }
        }

        class ResourceLoader<out T>(val id: Int) {
            operator fun provideDelegate(
                    thisRef: MyActivity,
                    prop: KProperty<*>
            ): ReadOnlyProperty<MyActivity, T> {
                return ResDelegate<T>(id)
            }

            private class ResDelegate<out V>(val id: Int) : ReadOnlyProperty<MyActivity, V> {
                val cacheKProperty = mutableMapOf<String, Any>()

                override fun getValue(thisRef: MyActivity, property: KProperty<*>): V {
                    val last = cacheKProperty[property.name]
                    if (last != null) {
                        return last as V
                    }
                    val value = when (property.returnType.classifier) {
                        String::class -> property.name as V
                        View::class -> View(id) as V
                        TextView::class -> TextView(id) as V
                        ImageView::class -> ImageView(id) as V
                        else -> throw NoSuchElementException()
                    }
                    cacheKProperty.put(property.name, value!!)
                    return value
                }
            }
        }
    }

}


fun main(args: Array<String>) {
    KotlinDelegation.MultipurposePrinter(KotlinDelegation.ColorPrinter()).print()
    KotlinDelegation.MultipurposePrinter(KotlinDelegation.BlackPrinter()).print()
    DelegationProperties.test()


}
