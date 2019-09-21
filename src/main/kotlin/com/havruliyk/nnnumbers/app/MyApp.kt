package com.havruliyk.nnnumbers.app

import com.havruliyk.nnnumbers.view.MainView
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = 700.0
        stage.height = 500.0
    }
}