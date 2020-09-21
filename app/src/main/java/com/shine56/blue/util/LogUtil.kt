package com.shine56.blue.util

import android.util.Log

object LogUtil {
    private val type = 0
    fun logD(tag: String, d: String){
        if (type < 1)
            Log.d(tag, d)
    }
    fun logD(d: String){
        if (type < 1)
            Log.d("调试", d)
    }

    fun logE(e: String){
        if (type < 1)
            Log.d("错误", e)
    }
}