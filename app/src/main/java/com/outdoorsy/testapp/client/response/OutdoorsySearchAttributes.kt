package com.outdoorsy.testapp.client.response

import com.google.gson.annotations.SerializedName

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

data class OutdoorsySearchAttributes(val name: String) {
    @SerializedName("primary_image_url")
    val primaryUrl: String? = null
}