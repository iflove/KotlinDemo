package demo

import FILE_NAME
import Hello.Root
import Hello.root
import PublicHelloWorld
import demo.chapter5.EmptyClass
import demo.chapter5.Father

//import InternalHelloWorld //Error: Cannot access 'InternalHelloWorld': it is internal in '<root>'

/**
 * Created by system on 2017/8/2.
 */

fun main(args: Array<String>) {
    root()
    println(Root())
    println(FILE_NAME)
    println(EmptyClass() is Any)

    //访问
    PublicHelloWorld()

    //
    Father().number
}