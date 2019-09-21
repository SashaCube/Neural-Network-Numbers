package com.havruliyk.nnnumbers.util

import com.havruliyk.nnnumbers.nn.Card
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

const val FILE_NAME = "data"

fun saveCards(cardList: MutableList<Card>, fileName: String = FILE_NAME) {
    val fos = FileOutputStream(fileName)
    val os = ObjectOutputStream(fos)
    os.writeObject(cardList)
    os.close()
    fos.close()
    println(cardList)

}

fun getCards(fileName: String = FILE_NAME): MutableList<Card> {
    val fis = FileInputStream(fileName)
    val inputStream = ObjectInputStream(fis)
    val cardList = inputStream.readObject() as MutableList<Card>
    inputStream.close()
    fis.close()
    println(cardList)

    return cardList
}