package com.shine56.blue.model.service

import retrofit2.await

object JsonNetWork {
    private val service = RetrofitCreator.create(JsonService::class.java)
    suspend fun getJson(getString: String)= service.getJson(getString).await()
}