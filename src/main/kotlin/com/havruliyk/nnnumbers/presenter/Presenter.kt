package com.havruliyk.nnnumbers.presenter

import com.havruliyk.nnnumbers.nn.Card
import com.havruliyk.nnnumbers.nn.Network
import com.havruliyk.nnnumbers.util.getArrayFromNumber
import com.havruliyk.nnnumbers.util.getCards
import com.havruliyk.nnnumbers.util.getNumberFromArray
import com.havruliyk.nnnumbers.util.saveCards
import java.util.*

class Presenter(val view: Contract.IView) : Contract.IPresenter {

    private var network: Network
    private lateinit var cardList: MutableList<Card>
    private lateinit var currentTestCard: Card

    init {
        loadData()
        network = Network()
    }

    private fun loadData() {
        cardList = try {
            getCards()
        } catch (e: Exception) {
            mutableListOf()
        } finally {
            mutableListOf<Card>()
        }
        println("LoadData: loaded ${cardList.size} cards ")
    }

    override fun onSaveTestData(input: Array<Float>, expectNumber: Int) {
        val card = Card(input, getArrayFromNumber(expectNumber))
        cardList.add(card)
        println("save")
        saveCards(cardList)
    }

    override fun onGetRandomTestData() {
        val index = Random().nextInt(cardList.size)

        println("getTestData: card #$index")
        currentTestCard = cardList[index]

        view.showTestView(
                cardList.size,
                getNumberFromArray(currentTestCard.output)
        )

        network.respond(currentTestCard)
        showNetwork()
    }

    override fun onGetNumber(input: Array<Float>) {
        network.respond(Card(input))
        showNetwork()
    }

    private fun showNetwork() = with(network) {
        view.showNetwork(
                inputLayer.map { it.output }.toTypedArray(),
                hiddenLayer.map { it.output }.toTypedArray(),
                outputLayer.map { it.output }.toTypedArray()
        )
    }

    override fun onTrain() {
        network.train(currentTestCard.output)
        onGetNumber(currentTestCard.inputs)
    }

    override fun onTrainAll() {
        cardList.map {
            network.respond(it)
            network.train(it.output)
        }
    }


}