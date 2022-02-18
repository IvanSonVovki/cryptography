package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.security.auth.kerberos.KerberosCredMessage


fun readImageShow(): BufferedImage {
    println("Input image file:")
    val pathStr = readLine()!!.toString()
    val pathImage = File(pathStr)
    return ImageIO.read(pathImage)
}

fun biteList(image: BufferedImage): ArrayList<Int> {

    var listCode = arrayListOf<Int>()
    for (y in 0 until image.height ) {
        for (x in 0 until image.width) {
            val color = Color(image.getRGB(x, y))

            val b = color.blue

            listCode += if (isEven(b)) 0 else 1
        }
    }
    return listCode
}

fun isEven(int: Int): Boolean {
    return (int and 1) != 1
}

fun returnListString(biteList: ArrayList<Int>): List<String> {

    var stringBuilder = StringBuilder("")
    var count = 0
    for (int in biteList) {
        ++count
        if (count % 8 != 0) {
            stringBuilder.append(int.toString())
        } else {
            stringBuilder.append("${int.toString()} ")
        }
    }
    return stringBuilder.split(" ")
} // +

fun byteList(biteGroup: List<String>): ArrayList<Int> {

    var byteList = arrayListOf<Int>()

    for (byte in biteGroup) {
        if (byte.length == 8) {
            byteList.add(byte.toInt(2))
        }
    }

    return byteList
}

fun returnStringMessage(messageByteArray: ByteArray): String {
    var message = StringBuilder(messageByteArray.decodeToString())
    message.delete(message.lastIndex - 2, message.lastIndex + 1)
    return message.toString()
}

fun creativeByteArrayFromList(list: ArrayList<Int>): ByteArray {
    var byteArray = byteArrayOf()

    for (int in list) {
        byteArray += int.toByte()
    }

    return byteArray
}



