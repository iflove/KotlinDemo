package demo.more

/**
 * Created by system on 2017/8/27.
 * 集合：List、Set、Map
 */
fun main(args: Array<String>) {
    val listItems = listOf(1, 2, 3)
    val mutableListItems = mutableListOf(1, 2, 3)

    val setItems = setOf(1, 2, 3)
    val mutableSetItems = mutableSetOf(1, 2, 3)

    val mapItems = mapOf("key" to "value")
    val mutableMapItems = mutableMapOf("key" to "value")
}

class Controller {
    private val _items = mutableListOf<String>()
    val items: List<String> get() = _items.toList()
}