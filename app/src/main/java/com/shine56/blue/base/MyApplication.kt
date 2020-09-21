package com.shine56.blue.base

import android.app.Application
import android.content.Context

class MyApplication : Application(){

    companion object{
        lateinit var context: Context
        var baseUrl: String = ""

        const val SUCCESS = 1
        const val FAIL = 0
        const val NOT_URL = -1
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}