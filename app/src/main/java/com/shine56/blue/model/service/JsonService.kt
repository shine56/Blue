package com.shine56.blue.model.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonService {
    @GET("{get}")
    fun getJson(@Path(value = "get", encoded = true) get: String): Call<ResponseBody>
}