package com.havruliyk.nnnumbers.view

import com.havruliyk.nnnumbers.presenter.Contract
import com.havruliyk.nnnumbers.presenter.Presenter
import tornadofx.*

class MainView : View("Neural Network Numbers"), Contract.IView {

    private val networkView = NetworkView()
    private val presenter: Contract.IPresenter = Presenter(this)

    override val root = vbox {
        hbox {
            networkView.view(this)
            addShortcuts()
        }
        hbox {

            paddingAll = 25

            button("Train") {
                setOnAction {
                    presenter.onTrainAll()
                }
                vboxConstraints {
                    marginLeftRight(10.0)
                }
                paddingAll = 5
            }

            button("Test") {
                setOnAction {
                    presenter.onGetNumber(networkView.getInputLayer())
                }
                vboxConstraints {
                    marginLeftRight(10.0)
                }
                paddingAll = 5
            }

            button("Random Number") {
                setOnAction {
                    presenter.onGetRandomTestData()
                }
                vboxConstraints {
                    marginLeftRight(10.0)
                }
                paddingAll = 5
            }

            button("Save Number") {
                setOnAction {
                    presenter.onSaveTestData(
                            networkView.getInputLayer(),
                            networkView.getExpectOutput()
                    )                }
                vboxConstraints {
                    marginLeftRight(10.0)
                }
                paddingAll = 5
            }

            button("Clear") {
                setOnAction {
                    networkView.clearAll()
                }
                vboxConstraints {
                    marginLeftRight(10.0)
                }
                paddingAll = 5
            }
        }
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