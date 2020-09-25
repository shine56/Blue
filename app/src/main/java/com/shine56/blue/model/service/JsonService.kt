package com.shine56.blue.model.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface JsonService {
    //@Headers("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
    @GET("{get}")
    fun getJson(@Path(value = "get", encoded = true) get: String): Call<ResponseBody>
}