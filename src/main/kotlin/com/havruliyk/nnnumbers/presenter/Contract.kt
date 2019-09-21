package com.havruliyk.nnnumbers.presenter

interface Contract {

    interface IView {

        fun showNetwork(
                inputArray: Array<Float>,
                hiddenArray: Array<Float>,
                outputArray: Array<Float>
        )

        fun showTestView(
                amountOfTestData: Int,
                correct: Int
        )
    }

    interface IPresenter{

        fun onSaveTestData(input: Array<Float>, expectNumber: Int)

        fun onGetRandomTestData()

        fun onGetNumber(input: Array<Float>)

        fun onTrain()

        fun onTrainAll()
    }
}