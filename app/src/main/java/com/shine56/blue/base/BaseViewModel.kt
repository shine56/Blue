package com.shine56.blue.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun executeInIO(block: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
        }
    }
}