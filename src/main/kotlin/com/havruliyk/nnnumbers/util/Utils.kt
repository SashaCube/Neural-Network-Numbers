package com.havruliyk.nnnumbers.util

import com.havruliyk.nnnumbers.nn.Card

val TEST_CARDS = {
    val cardList = mutableListOf<Card>()
    cardList.add(Card())
}

fun getArrayFromNumber(number: Int) = when (number) {
    0 -> arrayOf(1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F)
    1 -> arrayOf(-1F, 1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F)
    2 -> arrayOf(-1F, -1F, 1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F)
    3 -> arrayOf(-1F, -1F, -1F, 1F, -1F, -1F, -1F, -1F, -1F, -1F)
    4 -> arrayOf(-1F, -1F, -1F, -1F, 1F, -1F, -1F, -1F, -1F, -1F)
    5 -> arrayOf(-1F, -1F, -1F, -1F, -1F, 1F, -1F, -1F, -1F, -1F)
    6 -> arrayOf(-1F, -1F, -1F, -1F, -1F, -1F, 1F, -1F, -1F, -1F)
    7 -> arrayOf(-1F, -1F, -1F, -1F, -1F, -1F, -1F, 1F, -1F, -1F)
    8 -> arrayOf(-1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, 1F, -1F)
    else -> arrayOf(-1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, -1F, 1F)
}

fun getNumberFromArray(array: Array<Float>): Int {
    for ((index, value) in array.withIndex()){
        if (value == 1F){
            return index
        }
    }

    return 0
}

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)