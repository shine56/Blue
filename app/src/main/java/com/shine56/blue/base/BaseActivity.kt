package com.shine56.blue.base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.shine56.blue.util.MyCrashHandler

open class BaseActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "活动调试"
        const val TRANSPARENT_WHITE = 1
        const val TRANSPARENT_BLACK = 2
    }

    private val activityName = javaClass.simpleName

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        Log.d(TAG, "$activityName-->onCreate()")
        super.onCreate(savedInstanceState)
//        val handler = MyCrashHandler(this)
//        Thread.setDefaultUncaughtExceptionHandler(handler)
    }

    override fun onPause() {
        Log.d(TAG, "$activityName-->onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "$activityName-->onStop()")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "$activityName-->onRestart()")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "$activityName-->onDestroy()")
        super.onDestroy()
    }

    /**
     * 修改状态栏
     * @param type
     */
    fun resetStatusBar(type: Int) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
        if (type == TRANSPARENT_BLACK) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else if (type == TRANSPARENT_WHITE) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    /**
     * 界面跳转
     * @param clazz
     */
    fun startActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    /**
     * 携带参数跳转
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>?, bundle: Bundle) {
        val intent = Intent(this, clazz)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    /**
     * 携带动画跳转
     * @param clazz
     * @param options
     */
    fun startActivityWithOptions(clazz: Class<*>?, options: Bundle?) {
        val intent = Intent(this, clazz)
        options?.let { startActivity(intent, it) }
    }

    /**
     * 携带参数以及动画跳转
     */
    fun startActivityWithOptions(
        clazz: Class<*>?,
        bundle: Bundle?,
        options: Bundle?
    ) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        options?.let { startActivity(intent, it) }
    }

    /**
     * 包含回调函数跳转
     * @param clazz
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>?, requestCode: Int) {
        val intent = Intent(this, clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * 包含回调函数带参数跳转
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(
        clazz: Class<*>?,
        bundle: Bundle?,
        requestCode: Int
    ) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 包含回调函数带动画跳转
     * @param clazz
     * @param options
     */
    fun startActivityForResultWithOptions(
        clazz: Class<*>?,
        requestCode: Int,
        options: Bundle?
    ) {
        val intent = Intent(this, clazz)
        options?.let { startActivityForResult(intent, requestCode, it) }
    }

    /**
     * 包含回调函数带参数以及动画跳转
     * @param clazz
     * @param bundle
     * @param requestCode
     * @param options
     */
    fun startActivityForResultWithOptions(
        clazz: Class<*>?,
        bundle: Bundle?,
        requestCode: Int,
        options: Bundle?
    ) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        options?.let { startActivityForResult(intent, requestCode, it) }
    }

    /**
     * 展示短时Toast
     * @param text
     */
    fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 打印信息
     * @param text
     */
    fun logD(text: String) {
        Log.d(TAG, "$activityName-->$text")
    }
}