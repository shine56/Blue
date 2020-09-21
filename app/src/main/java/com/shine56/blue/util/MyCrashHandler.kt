package com.shine56.blue.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.shine56.blue.ui.ErrorLogActivity
import java.io.*

class MyCrashHandler(private val mContext: Activity) : Thread.UncaughtExceptionHandler{

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e("程序出现异常了", "Thread = " + t.name + "\nThrowable = " + e.message)

        val stackTraceInfo = getStackTraceInfo(e)
        Log.e("stackTraceInfo", stackTraceInfo)
        val intent = Intent(mContext, ErrorLogActivity::class.java)
        intent.putExtra("errorInfo", stackTraceInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // 如果设置了此标志，这个activity将成为一个新task的历史堆栈中的第一个activity
        mContext.startActivity(intent)
    }

    /**
     * 获取错误的信息
     *
     * @param throwable
     * @return
     */
    private fun getStackTraceInfo(throwable: Throwable): String {
        var pw: PrintWriter? = null
        val writer: Writer = StringWriter()
        try {
            pw = PrintWriter(writer)
            throwable.printStackTrace(pw)
        } catch (e: Exception) {
            return ""
        } finally {
            pw?.close()
        }
        return writer.toString()
    }
}