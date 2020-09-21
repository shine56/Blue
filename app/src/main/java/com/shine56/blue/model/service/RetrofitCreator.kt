package com.shine56.blue.model.service

import com.shine56.blue.base.MyApplication
import retrofit2.Retrofit

object RetrofitCreator {
    fun<T> create(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(MyApplication.baseUrl)
            .build()
        return retrofit.create(serviceClass)
    }
}