package com.havruliyk.nnnumbers.nn

import java.util.*

class Neuron(
        var inputNeuron: MutableList<Neuron> = mutableListOf(),
        var weights: MutableList<Float> = mutableListOf(),
        var output: Float = 0F
) {

    private var error = 0F

    init {
        val r = Random()
        for (i in inputNeuron) {
            weights.add(MIN_VALUE + r.nextFloat() * (MAX_VALUE - MIN_VALUE))
        }
    }

    fun respond() {
        var inputSum = 0F

        for ((index, neuron) in inputNeuron.withIndex()) {
            inputSum += neuron.output * weights[index]
        }

        output = Sigmoid.INSTANCE.lookupSigmoid(inputSum)

        error = 0F
    }

    fun setError(desired: Float) {
        error = desired - output
    }

    fun train() {
        val delta = (1F - output) * (1F + output) * error * LEARNING_RATE

        for ((index, neuron) in inputNeuron.withIndex()) {
            neuron.error += weights[index] * error
            weights[index] += (neuron.output * delta).toFloat()
        }
    }

    companion object {
        const val LEARNING_RATE = 0.01
        const val MIN_VALUE = -1F
        const val MAX_VALUE = 1F
    }
}