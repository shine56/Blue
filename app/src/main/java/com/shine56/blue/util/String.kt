package com.shine56.blue.util

import android.widget.Toast
import com.shine56.blue.base.MyApplication

fun String.logD(){
    LogUtil.logD(this)
}
fun String.logD(tag: String){
    LogUtil.logD(tag, this)
}

fun String.toast(){
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}