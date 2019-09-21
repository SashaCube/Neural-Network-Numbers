package com.havruliyk.nnnumbers.view

import com.havruliyk.nnnumbers.presenter.Contract
import com.havruliyk.nnnumbers.presenter.Presenter
import tornadofx.*

class MainView : View("Neural Network Numbers"), Contract.IView {

    private val networkView = NetworkView()
    private val presenter: Contract.IPresenter = Presenter(this)

    override val root = hbox {
        networkView.view(this)
        addShortcuts()
    }

    private fun addShortcuts() {
        shortcut("Ctrl + R") {
            networkView.clearAll()
        }
        shortcut("Ctrl + S") {
            presenter.onSaveTestData(
                    networkView.getInputLayer(),
                    networkView.getExpectOutput()
            )
        }
        shortcut("Ctrl + T") {
            presenter.onGetRandomTestData()
        }
        shortcut("Ctrl + Q") {
            presenter.onTrain()
        }
        shortcut("Ctrl + W") {
            presenter.onTrainAll()
        }
        shortcut("Enter") {
            presenter.onGetNumber(networkView.getInputLayer())
        }
    }

    override fun showNetwork(inputArray: Array<Float>, hiddenArray: Array<Float>, outputArray: Array<Float>) {
        networkView.showInputLayer(inputArray)
        networkView.showHiddenLayer(hiddenArray)
        networkView.showOutputLayer(outputArray)
    }

    override fun showTestView(amountOfTestData: Int, correct: Int) {
        networkView.showExpectedNumber(correct)
        networkView.showNumberOfTestData(amountOfTestData)
    }
}