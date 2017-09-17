/**
 * Created by system on 2017/8/2.
 */

internal class InternalHelloWorld

class PublicHelloWorld

private class PrivateHelloWorld : IPrivateHelloWorld{
    val a = 1
}

private interface IPrivateHelloWorld {

    private fun privateHello(): Unit {

    }

     fun hello(): Unit {

    }
}

fun main(args: Array<String>) {
    PublicHelloWorld()
    InternalHelloWorld()
    PrivateHelloWorld()
    PrivateHelloWorld().a
//    PrivateHelloWorld().privateHello()
    PrivateHelloWorld().hello()
}