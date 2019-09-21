package com.havruliyk.nnnumbers.view

import javafx.scene.text.Font
import tornadofx.*

class MainView : View("Neural Network Numbers") {

    private val networkView = NetworkView()

    override val root = hbox {
        networkView.view(this)
        numberView()
        addShortcuts()
    }

    private fun numberView() {
        label {
            paddingAll = 25
            text = "2"
            font = Font.font(350.0)
        }
    }

    private fun addShortcuts() {
        shortcut("Ctrl + R") {
            networkView.clearAll()
        }
    }
}