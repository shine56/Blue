package com.shine56.blue.ui.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shine56.blue.base.BaseViewModel
import com.shine56.blue.base.MyApplication
import com.shine56.blue.base.MyApplication.Companion.FAIL
import com.shine56.blue.base.MyApplication.Companion.NOT_URL
import com.shine56.blue.base.MyApplication.Companion.SUCCESS
import com.shine56.blue.model.bean.RequestBean
import com.shine56.blue.model.repository.Repository
import com.shine56.blue.model.service.JsonNetWork
import com.shine56.blue.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RequestVm: BaseViewModel() {
    val text = MutableLiveData<String>()

    var id: Int = 0

    val requestBean = MutableLiveData<RequestBean>()
    val resultCode = MutableLiveData<Int>()
    val saveCode = MutableLiveData<Int>()

    fun refreshBean() {

        if(id == 0){
            requestBean.value = RequestBean()
        }else{
            executeInIO { requestBean.postValue(Repository.loadRequestBeanById(id)) }
        }
    }

    fun request(){
        //url为空
        val baseUrl = requestBean.value!!.baseUrl
        val getString = requestBean.value!!.get
        if(baseUrl == "" || getString == ""){
            resultCode.value = NOT_URL
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            try {
                //IO线程发起请求
                val result = withContext(Dispatchers.IO){
                    MyApplication.baseUrl = baseUrl
                    JsonNetWork.getJson(getString)
                }
                text.value = result.string()
                resultCode.value = SUCCESS
            }catch (e: Exception){
                text.value = e.message
                e.message?.logD("请求出错")
                resultCode.value = FAIL
            }
        }
    }

    fun save(){
        viewModelScope.launch {
            try {
                //IO线程
                viewModelScope.launch(Dispatchers.IO){
                    if(id == 0){
                        Repository.saveRequestBean(requestBean.value!!)
                    }else{
                        Repository.updateRequest(requestBean.value!!)
                    }
                }
                saveCode.value = SUCCESS
            }catch (e: Exception){
                e.message?.logD("保存出错")
                saveCode.value = FAIL
            }
        }

    }
}