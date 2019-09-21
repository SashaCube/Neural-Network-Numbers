package com.havruliyk.nnnumbers.view

import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

class NetworkView {

    val circleRadius = 13.0
    val circlesList = mutableListOf<Circle>()
    var isMousePressed = false

    fun view(parent: HBox) = with(parent) {
        vbox {
            paddingAll = 25
            for (k in 1..14) {
                hbox {
                    circleBoard(circleRadius, 14)
                }
                addEventFilter(MouseEvent.MOUSE_MOVED) {
                    isMousePressed = it.isAltDown
                }
            }
            label("To draw press Alt and move mouse, to clear press Ctrl+R")
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

    fun clearAll() {
        circlesList.map { it.fill = Color.WHITE }
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
}