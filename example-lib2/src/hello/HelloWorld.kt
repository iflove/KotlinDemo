package hello

import InternalHelloWorld
import PublicHelloWorld

//import PrivateHelloWorld //Error: Cannot access 'PrivateHelloWorld': it is private in file

/**
 * Created by system on 2017/8/8.
 */


fun main(args: Array<String>) {
    PublicHelloWorld()
    InternalHelloWorld()
//    PrivateHelloWorld()

}
