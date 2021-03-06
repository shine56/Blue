package com.shine56.blue.model.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
//
interface JsonService {
    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36"
//        "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
//        "accept-encoding: gzip, deflate, br",
//        "accept-language: zh-CN,zh;q=0.9",
//        "cache-control: max-age=0",
//        "referer: https://www.pexels.com/zh-cn/",
//        "sec-fetch-dest: document",
//        "sec-fetch-mode: navigate",
//        "sec-fetch-site: same-origin",
//        "sec-fetch-user: ?1",
//        "upgrade-insecure-requests: 1"
    )

    @GET("{get}")
    fun getJson(@Path(value = "get", encoded = true) get: String): Call<ResponseBody>
}