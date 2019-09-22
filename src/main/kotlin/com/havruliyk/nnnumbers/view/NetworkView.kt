package com.havruliyk.nnnumbers.view

import com.havruliyk.nnnumbers.util.format
import com.havruliyk.nnnumbers.util.getGrayColorByValue
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import tornadofx.*

class NetworkView {

    private val circleRadius = 13.0

    private val circlesListInput = mutableListOf<Circle>()
    private val circlesListHidden = mutableListOf<Circle>()
    private val circlesListOutput = mutableListOf<Circle>()

    private var isMousePressed = false
    private lateinit var expectNumberField: TextField
    private lateinit var amountOfTestDataLabel: Label
    private lateinit var expectNumberLabel: Label
    private val outputStaticLabels = mutableListOf<Label>()

    fun view(parent: HBox) = with(parent) {
        vbox {
            paddingAll = 25
            for (k in 1..14) {
                hbox {
                    circleBoard(circlesListInput, circleRadius, 14)
                }
                addEventFilter(MouseEvent.MOUSE_MOVED) {
                    isMousePressed = it.isAltDown
                }
            }

            label("Output Layer") {
                vboxConstraints {
                    marginTop = 10.0
                }
            }

            label {
                text = "To draw press Alt and move mouse, to clear press Ctrl+R\n" +
                        "To get random test hand write number press Ctrl+T\n" +
                        "To save your hand write number to test data press Ctrl+S\n"

                vboxConstraints {
                    marginTop = 10.0
                }
            }

            expectNumberField = textfield {
                prefWidth = 300.0
                text = "2"
                font = Font.font(50.0)

                vboxConstraints {
                    marginTop = 10.0
                }
            }
        }

        vbox {
            paddingAll = 25
            for (k in 1..7) {
                hbox {
                    circleBoard(circlesListHidden, circleRadius, 7)
                }
            }

            label {
                text = "Hidden Layer"
                vboxConstraints {
                    marginTop = 10.0
                }
            }
        }

        vbox {
            paddingAll = 25
            for (k in 1..10) {
                hbox {
                    label((k - 1).toString()) {
                        paddingAll = 10
                    }
                    circleBoard(circlesListOutput, circleRadius, 1)
                    val label = label {
                        paddingAll = 10
                    }
                    outputStaticLabels.add(label)
                }
            }

            label("Output Layer") {
                vboxConstraints {
                    marginTop = 10.0
                }
            }
        }
        label("Train data")
        amountOfTestDataLabel = label {
            paddingAll = 25
        }

        label("Expect Number")
        expectNumberLabel = label {
            paddingAll = 25
        }
    }

    fun showInputLayer(array: Array<Float>) {
        array.mapIndexed { index, fl -> circlesListInput[index].setColor(fl) }
    }

    fun showHiddenLayer(array: Array<Float>) {
        array.mapIndexed { index, fl -> circlesListHidden[index].setColor(fl) }
    }

    fun showOutputLayer(array: Array<Float>) {
        val max = 1F
        val min = -1F

        array.mapIndexed { index, fl ->
            circlesListOutput[index].setColor(-fl)
            outputStaticLabels[index].text = "${((fl + 1) / 2).times(100).format(2)} %"
        }
    }

    fun showExpectedNumber(correct: Int) {
        expectNumberLabel.text = correct.toString()
    }

    fun showNumberOfTestData(amount: Int) {
        amountOfTestDataLabel.text = amount.toString()
    }

    private fun Circle.onMouseEntered() {

        if (isMousePressed) {
            fill = when (fill) {
                Color.WHITE -> Color.GRAY
                Color.GRAY -> Color.BLACK
                else -> Color.BLACK
            }
        }
    }

    private fun Circle.value() = when (fill) {
        Color.WHITE -> 1F
        Color.BLACK -> -1F
        else -> 0F
    }

    private fun Circle.setColor(float: Float) {
        fill = getGrayColorByValue(float)

//                if (float > 0F) {
//            Color.WHITE
//        } else {
//            if (float < 0F) {
//                Color.BLACK
//            } else {
//                Color.GRAY
//            }
//        }
    }

    fun getInputLayer(): Array<Float> {
        val input = Array(196) { it.toFloat() }
        circlesListInput.mapIndexed { index, circle ->
            input[index] = circle.value()
        }

        return input
    }

    fun getExpectOutput() = expectNumberField.text.toInt()

    fun clearAll() {
        circlesListInput.map { it.fill = Color.WHITE }
    }

    private fun HBox.circleBoard(list: MutableList<Circle>, circleRadius: Double, count: Int) {

        for (i in 1..count) {
            val singleCircle = circle {
                paddingAll = 0.1 * circleRadius
                radius = circleRadius
                centerX = circleRadius / 2
                centerY = circleRadius / 2 + circleRadius * i
                strokeWidth = 1.0
                stroke = Color.GRAY
                fill = Color.WHITE

                setOnMouseEntered {
                    onMouseEntered()
                }
            }

            list.add(singleCircle)
        }
    }
}