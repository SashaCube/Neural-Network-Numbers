package com.havruliyk.nnnumbers.nn

class Network(
        val inputLayer: MutableList<Neuron> = mutableListOf()
) {
    init {
        for (i in 0..NUMBER_OF_NEURONS) {
            inputLayer.add(Neuron(0F))
        }
    }

    private fun respond(card: Card) {
        inputLayer.mapIndexed { index, neuron ->
            neuron.output = card.inputs[index]
        }
    }

    companion object {
        const val NUMBER_OF_NEURONS = 192
    }
}