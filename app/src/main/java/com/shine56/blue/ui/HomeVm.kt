package com.shine56.blue.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shine56.blue.base.BaseViewModel
import com.shine56.blue.model.bean.RequestBean
import com.shine56.blue.model.repository.Repository
import com.shine56.blue.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeVm : BaseViewModel() {
    val taskList = MutableLiveData<List<RequestBean>>()
     var deleteState = MutableLiveData<Boolean>()

    fun loadTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val beans = Repository.loadAllRequestBeans()
            taskList.postValue(beans)
        }
    }

    fun deleteTask(bean: RequestBean){
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO){
                    Repository.deleteRequestBean(bean)
                }
                deleteState.value = true
            }catch (e: Exception){
                e.message?.logD()
                deleteState.value = false
            }

        }
    }
}