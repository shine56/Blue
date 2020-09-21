package com.shine56.blue.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    companion object{
        private const val TAG = "碎片调试"
        const val TRANSPARENT_WHITE = 1
        const val TRANSPARENT_BLACK = 2
    }
    
    private val fragmentName = javaClass.simpleName
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$fragmentName-->onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(
            TAG,
            "$fragmentName-->onCreateView()"
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(
            TAG,
            "$fragmentName-->onActivityCreated()"
        )
    }

    override fun onResume() {
        super.onResume()
        Log.d(
            TAG,
            "$fragmentName-->onResume()"
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d(
            TAG,
            "$fragmentName-->onPause()"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.d(
            TAG,
            "$fragmentName-->onStop()"
        )
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            Log.d(
                TAG,
                "$fragmentName-->onHiddenChanged()-->隐藏"
            )
        } else {
            Log.d(
                TAG,
                "$fragmentName-->onHiddenChanged()-->显示"
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(
            TAG,
            "$fragmentName-->onDestroy()"
        )
    }
    /**
     * 展示短时Toast
     * @param text
     */
    fun showToast(text: String?) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 直接跳转
     * @param clazz
     */
    fun startActivity(clazz: Class<*>?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }

    /**
     * 携带参数跳转
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(activity, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 携带参数以及动画跳转
     */
    fun startActivityWithOptions(
        clazz: Class<*>?,
        bundle: Bundle,
        options: Bundle
    ) {
        val intent = Intent(activity, clazz)
        intent.putExtras(bundle)
        startActivity(intent, options)
    }

    /**
     * 打印信息
     * @param text
     */
    fun logD(text: String) {
        Log.d(
            TAG,
            "$fragmentName-->$text"
        )
    }
}