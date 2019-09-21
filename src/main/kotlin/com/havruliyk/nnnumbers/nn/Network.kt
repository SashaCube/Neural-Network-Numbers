package com.havruliyk.nnnumbers.nn

class Network(
        val inputLayer: MutableList<Neuron> = mutableListOf(),
        val hiddenLayer: MutableList<Neuron> = mutableListOf(),
        val outputLayer: MutableList<Neuron> = mutableListOf()
) {
    init {
        for (i in 1..NUMBER_OF_INPUT_NEURONS) {
            inputLayer.add(Neuron())
        }
        for (i in 1..NUMBER_OF_HIDDEN_NEURONS) {
            hiddenLayer.add(Neuron(inputLayer))
        }
        for (i in 1..NUMBER_OF_OUTPUT_NEURONS) {
            outputLayer.add(Neuron(hiddenLayer))
        }
    }

    fun respond(card: Card) {
        inputLayer.mapIndexed { index, neuron ->
            neuron.output = card.inputs[index]
        }
        hiddenLayer.map { it.respond() }
        outputLayer.map { it.respond() }
    }

    fun train(outputs: Array<Float>) {
        outputLayer.mapIndexed { index, neuron ->
            neuron.setError(outputs[index])
            neuron.train()
        }
        hiddenLayer.map { it.train() }
    }

    companion object {
        const val NUMBER_OF_INPUT_NEURONS = 196
        const val NUMBER_OF_HIDDEN_NEURONS = 49
        const val NUMBER_OF_OUTPUT_NEURONS = 10
    }
}