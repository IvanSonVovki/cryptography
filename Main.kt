package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.security.MessageDigest
import javax.imageio.ImageIO


fun main() {
    menu()
}

fun menu() {
    println("Task (hide, show, exit):")
    when (val input = readLine()!!.toString()) {
        "hide" -> hide()
        "show" -> show()
        "exit" -> exit()
       // "hash" -> hash()
        else -> wrongTask(input) //C:\Users\Ivan\IdeaProjects\Steganography and Cryptography\purple-flowers.png
    }
}

fun hash() {
     fun imageHash(inputImage: BufferedImage) : String {
        val imageByteArray = ByteArray(3 * inputImage.width * inputImage.height)
        var index = 0
        for (y in 0 until inputImage.height) {
            for (x in 0 until inputImage.width) {
                val color = Color(inputImage.getRGB(x, y))
                imageByteArray[index] = color.red.toByte()
                index++
                imageByteArray[index] = color.green.toByte()
                index++
                imageByteArray[index] = color.blue.toByte()
                index++
            }
        }
        val md = MessageDigest.getInstance("SHA-1")
        md.update(imageByteArray)
        return md.digest().joinToString("") { "%02x".format(it) }
    }
    val pathImageFileOut = File("C:\\Users\\Ivan\\IdeaProjects\\Steganography and Cryptography\\out.png")
     val imageInput: BufferedImage = ImageIO.read(pathImageFileOut)
     println(imageHash(imageInput))

}


fun hide() {

    var image: BufferedImage? = null
    try {
        image = readImageHide()   // read Image

    } catch (e: Exception) {
        println(e.message)
        menu()
        return
    }
    // read path save File Image
    val pathImageFileOut = pathOutFileSet()
    // read message to coding
    val message = readMessage()
    // read password
    val password = readPassword()
    // creating a byte array a message
    val byteArrayMessage = codingToByteArrayMessage(message)
    // creating a byte array of a message encrypted with a password
    val codingMessage = creativeByteList(byteArrayMessage, password)
    // creating a bite list from byte array
    val codingBiteList = codingBiteMessage(codingMessage)

    if (codingBiteList.size < (image.height * image.width)) {

        codingImage(image, codingBiteList, pathImageFileOut)

        println("Message saved in $pathImageFileOut image.")

    } else {

        println("The input image is not large enough to hold this message.")
    }

    menu()

}



fun show() {

    var image: BufferedImage? = null
    try {
        image = readImageShow() // create image: BufferedImage
    }catch (e:Exception) {
        println(e.message)
        menu()
        return
    }
    val password = readPassword()   // read password
    val biteList = biteList(image) // creative bite list message from image
    val groupBiteList = returnListString(biteList) // list group 8 bit
    val byteCharList = byteList(groupBiteList)    // list byte (Char code)

    val byteArrayMessage = creativeByteArrayFromList(byteCharList)
    val byteArrayMessageDecoding = creativeByteList(byteArrayMessage, password, true)

    val message = returnStringMessage(byteArrayMessageDecoding) // convert char code in string
    println("Message:")
    println(message)
    menu()
}

fun exit() {
    println("Bye!")
}

fun wrongTask(input: String) {
    println("Wrong task: $input")
    menu()
}

