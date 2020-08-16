package com.outdoorsy.testapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.outdoorsy.testapp.TestApp
import com.outdoorsy.testapp.client.OutdoorsySearchClient
import com.outdoorsy.testapp.client.response.OutdoorsyResponse
import com.outdoorsy.testapp.client.response.OutdoorsySearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val searchClient: OutdoorsySearchClient? = TestApp.component?.outdoorsySearchClient()

    val searchStatus = MutableLiveData<SearchStatus>()

    init {
        searchStatus.value =
            SearchStatus.Empty
    }

    fun search(keyword: String) {
        searchStatus.value =
            SearchStatus.Loading
        searchClient?.search(keyword, null, null)?.enqueue(object : Callback<OutdoorsyResponse> {
            override fun onFailure(call: Call<OutdoorsyResponse>, t: Throwable) {
                searchStatus.value =
                    SearchStatus.Error(
                        t.localizedMessage ?: "Throwable error " + t.message
                    )
            }

            override fun onResponse(call: Call<OutdoorsyResponse>, response: Response<OutdoorsyResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        searchStatus.value =
                            SearchStatus.Loaded(
                                response.body()?.data!!
                            )
                    } else {
                        searchStatus.value =
                            SearchStatus.Error("No search results")
                    }
                } else {
                    searchStatus.value =
                        SearchStatus.Error(
                            "Searching error"
                        )
                }
            }
        })
    }

    sealed class SearchStatus {
        object Empty : SearchStatus()
        object Loading : SearchStatus()
        data class Loaded(val result: List<OutdoorsySearchResult>) : SearchStatus()
        data class Error(val error: String) : SearchStatus()
    }
}