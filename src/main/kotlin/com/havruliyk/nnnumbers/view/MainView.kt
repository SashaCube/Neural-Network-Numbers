package com.havruliyk.nnnumbers.view

import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Font
import tornadofx.*

class MainView : View("Neural Network Numbers") {

    val circleRadius = 13.0
    val circlesList = mutableListOf<Circle>()
    var isMousePressed = false

    override val root = hbox {
        vbox {
            paddingAll = 25
            for (k in 1..14) {
                hbox {
                    circleBoard(circleRadius, 14)
                }
                addEventFilter(MouseEvent.MOUSE_MOVED) {
                    isMousePressed = it.isAltDown
                }
                shortcut("Ctrl+R") {
                    clearAll()
                }
                shortcut("Enter") {
                    // TODO: get number
                }
                shortcut("Ctrl+S") {
                    // TODO: save number
                }
            }
            label("To draw press Alt and move mouse, to clear press Ctrl+R")
        }

        label{
            paddingAll = 25
            text = "2"
            font = Font.font(350.0)
        }
    }

    private fun HBox.circleBoard(circleRadius: Double, count: Int) {

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

            circlesList.add(singleCircle)
        }
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

    private fun clearAll() {
        circlesList.map { it.fill = Color.WHITE }
    }
}