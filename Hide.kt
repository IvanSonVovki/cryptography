package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun createBite(byte: Byte): String {
    var str = byte.toString(2)

    while (str.length < 8) {
        str = "0$str"
    }
    return str
}

fun setCodingBlue(blue: Int, coding: Int): Int {
    var b = blue

    if (isEven(b)) {
        b = b xor coding
    } else {

        when (coding) {
            0 -> b = b xor 1
            1 -> b = b xor 0
        }
    }

    return b
}

fun codingToByteArrayMessage(message: String): ByteArray {
 //   val endByteArray = byteArrayOf(0.toByte(), 0.toByte(), 3.toByte())
    val messageByteArray = message.encodeToByteArray()
    return messageByteArray// + endByteArray
}

fun codingBiteMessage(message: ByteArray): ArrayList<Int> {

    val stringBite = message.map { createBite(it) }

    val intBiteList = arrayListOf<Int>()
    for (str in stringBite) {
        for (char in str) {
            intBiteList += char.digitToInt()
        }
    }
    return intBiteList
}

fun codingImage(image: BufferedImage, biteList: ArrayList<Int>, pathOut: String) {
    var count = 0
    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (count < biteList.size) {
                val color = Color(image.getRGB(x, y))
                val r = color.red
                val g = color.green
                var b = color.blue
                b = setCodingBlue(b, biteList[count])
                ++count
                val newColor = Color(r, g, b)
                image.setRGB(x, y, newColor.rgb)

            }
        }
    }
    val pathOutFile = File(pathOut)
    ImageIO.write(image, "png", pathOutFile)
}

fun readImageHide(): BufferedImage {

    println("Input image file:")
    val pathImageFileInputStr = readLine()!!.toString()
    val pathImageFileInput = File(pathImageFileInputStr)

    return ImageIO.read(pathImageFileInput)
}

fun pathOutFileSet(): String {

    println("Output image file:")
    val pathImageFileOutStr = readLine()!!.toString()
    val pathImageFileOut = File(pathImageFileOutStr)
    return pathImageFileOutStr
}

fun readMessage(): String {
    println("Message to hide:")
    return readLine()!!.toString()
}

