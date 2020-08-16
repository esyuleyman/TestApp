package com.outdoorsy.testapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.outdoorsy.testapp.client.response.OutdoorsySearchResult
import com.outdoorsy.testapp.ui.adapters.viewholders.MainFragmentRvVh

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

class MainFragmentListAdapter: ListAdapter<OutdoorsySearchResult, MainFragmentRvVh>(ItemsDiffCallback()) {

    class ItemsDiffCallback : DiffUtil.ItemCallback<OutdoorsySearchResult>() {
        override fun areItemsTheSame(
            oldItem: OutdoorsySearchResult,
            newItem: OutdoorsySearchResult
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: OutdoorsySearchResult,
            newItem: OutdoorsySearchResult
        ): Boolean  = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentRvVh {
        return MainFragmentRvVh(parent)
    }

    override fun onBindViewHolder(holder: MainFragmentRvVh, position: Int) {
        holder.bind(getItem(position), position)
    }
}