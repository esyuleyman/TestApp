package com.outdoorsy.testapp.client

import com.outdoorsy.testapp.client.response.OutdoorsyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

@Singleton
class OutdoorsySearchClient {
    companion object {
        private const val BASE_URL = "https://search.outdoorsy.co/"

        private const val CONNECT_TIMEOUT = 30L     // in seconds
        private const val READ_TIMEOUT = 30L        // in seconds
        private const val WRITE_TIMEOUT = 30L       // in seconds

        private var apiFunction: OutdoorsySearchApi? = null

        private fun initAPI() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            apiFunction = retrofit.create(OutdoorsySearchApi::class.java)
        }

        init {
            initAPI()
        }
    }

    fun search(
        keyword: String,
        limit: Int?,
        offset: Int?,
        address: String?
    ): Call<OutdoorsyResponse> {
        return apiFunction?.search(keyword, limit, offset, address)!!
    }

}
