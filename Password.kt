package cryptography

import kotlin.experimental.xor

fun readPassword(): String {

    println("Password:")
    return readLine()!!.toString()
}

fun searchStop(biteArray: ByteArray): Boolean {
    var isFirstMath = false
    var isSecondMath = false
    var isLastMath = false

    loop@ for (byte in biteArray) {
        if (0.toByte() == byte) {
            if (0.toByte() == byte && isFirstMath) {

                isSecondMath = true
            } else {
                isFirstMath = true
            }
        }
        if (3.toByte() == byte && isSecondMath) {
            isLastMath = true
            break@loop
        }
    }

    return isLastMath
}

fun creativeByteList(byteArray: ByteArray, password: String, short: Boolean = false): ByteArray {

    val passwordByte = password.encodeToByteArray()
    val endByteArray = byteArrayOf(0.toByte(), 0.toByte(), 3.toByte())
    var codingByte = byteArrayOf()
    loop@ for (i in byteArray.indices) {
        val indexPassword = (i) % passwordByte.size

        codingByte += byteArray[i] xor passwordByte[indexPassword]

        if (short) {
            if (searchStop(codingByte)) {
                break@loop
            }
        }
    }

    return if (short) codingByte else codingByte.plus(endByteArray)
}