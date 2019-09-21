package com.havruliyk.nnnumbers.nn

import java.io.Serializable

data class Card(
        var inputs: Array<Float> = arrayOf(),
        var output: Array<Float> = arrayOf()
) : Serializable {
    //TODO add func to load input

    //TODO add func to load weights
}