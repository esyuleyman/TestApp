package com.outdoorsy.testapp.client.response

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

data class OutdoorsySearchResult(val id: Long, val type: String) {
    val attributes: OutdoorsySearchAttributes? = null
}