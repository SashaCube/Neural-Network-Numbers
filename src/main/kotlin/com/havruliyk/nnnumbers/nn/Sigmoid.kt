package com.havruliyk.nnnumbers.nn

import java.lang.Math.exp
import java.lang.Math.floor

class Sigmoid {

    private var sigmoid = mutableListOf<Float>()

    init {
        for (i in 1..MAX_OF_SIGMOID) {

            val x = i / 20.0 - 5.0
            sigmoid.add((2.0 / (1.0 + exp(-2.0 * x)) - 1.0).toFloat())
        }
    }

    fun lookupSigmoid(x: Float): Float {
        val value = floor((x + 5.0) * 20.0).toInt()

        val index = if (value >= MAX_OF_SIGMOID) {
            MAX_OF_SIGMOID - 1
        } else {
            if (value <= MIN_OF_SIGMOID) {
                MIN_OF_SIGMOID + 1
            } else {
                value
            }
        }

        return sigmoid[index]
    }

    companion object {
        const val MAX_OF_SIGMOID = 200
        const val MIN_OF_SIGMOID = 0

        var INSTANCE = Sigmoid()
    }
}