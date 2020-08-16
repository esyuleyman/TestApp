package com.outdoorsy.testapp.client

import com.outdoorsy.testapp.client.response.OutdoorsyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

interface OutdoorsySearchApi {

    @GET("rentals")
    fun search(@Query("filter[keywords]") keyword: String,
               @Query("page[limit]") limit: Int?,
               @Query("page[offset]") offset: Int?
    ): Call<OutdoorsyResponse>

}