package com.shine56.blue.ui.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shine56.blue.base.BaseViewModel
import com.shine56.blue.base.MyApplication
import com.shine56.blue.model.bean.RequestBean
import com.shine56.blue.model.repository.Repository
import com.shine56.blue.model.service.JsonNetWork
import com.shine56.blue.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RequestViewModel : BaseViewModel() {
    val text = MutableLiveData<String>()

    private lateinit var requestBean: RequestBean

    init {
        MyApplication.baseUrl = requestBean.baseUrl
        viewModelScope.launch(Dispatchers.IO) {
            requestBean = Repository.loadRequestBeanById(1)
        }
    }

    fun request(){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                //IO线程发起请求
                val result = withContext(Dispatchers.IO){
                    JsonNetWork.getJson(requestBean.get)
                }
                text.value = result.string()
            }catch (e: Exception){
                e.message?.logD("请求出错")
            }
        }
    }
}