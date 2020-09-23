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
import com.shine56.blue.util.JsonUtil
import com.shine56.blue.util.StringUtil
import com.shine56.blue.util.logD
import com.shine56.blue.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RequestVm: BaseViewModel() {
    val text = MutableLiveData<String>()
    val arrangeText = MutableLiveData<String>()

    var id: Int = 0
    var selectTool = -1

    val requestBean = MutableLiveData<RequestBean>()
    val resultCode = MutableLiveData<Int>()
    val saveCode = MutableLiveData<Int>()

    val jsonUtil by lazy { JsonUtil(text.value!!) }

    /**
     * 初始化
     */
    fun refreshBean() {

        if(id == 0){
            requestBean.value = RequestBean()
        }else{
            executeInIO { requestBean.postValue(Repository.loadRequestBeanById(id)) }
        }
    }

    /**
     * 发起请求
     */
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

    /**
     * 保存数据库
     */
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

    fun arrange(input: String){
        when(selectTool){
            0 -> {
                decodeUnicode()
                "转换结束".toast()
            }
            1 -> {
                deleteStr(input)
                "删除".toast()
            }
            2 -> {
                val regex = "**"
                val strList = input.split(regex)
                replaceStr(strList[0], strList[1])
                "替换".toast()
            }
            3 -> {
                match(input)
                "正则".toast()
            }
        }
    }

    /**
     * 正则
     */
    private fun match(sign: String){
        var resultText = ""
        val matcher =  jsonUtil.getContentByName(sign)
        while (matcher.find()){
            resultText += matcher.group()
        }
        arrangeText.value = resultText
    }

    /**
     * 去除某字符
     */
    private fun deleteStr(str: String){
        arrangeText.value = StringUtil.deleteString(str, arrangeText.value!!)
    }
    /**
     * 替换某字符
     */
    private fun replaceStr(oldStr: String, newString: String){
        arrangeText.value = StringUtil.replaceString(oldStr, newString, arrangeText.value!!)
    }

    /**
     * unicode转中文
     */
    private fun decodeUnicode(){
        val result = StringUtil.decodeUnicode(arrangeText.value!!)
        if(result != "") result
        else text.value
    }
}