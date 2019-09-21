package com.havruliyk.nnnumbers.presenter

import com.havruliyk.nnnumbers.nn.Network

class Presenter{

    private lateinit var network: Network

    private fun setup(){
        loadData()
        initNetwork()
    }

    private fun loadData(){
        //TODO load saved data for test and learn network
    }

    private fun initNetwork(){
        //TODO init network with loaded data
    }
}