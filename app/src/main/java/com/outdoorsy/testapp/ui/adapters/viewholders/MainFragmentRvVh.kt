package com.outdoorsy.testapp.ui.adapters.viewholders

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.outdoorsy.testapp.R
import com.outdoorsy.testapp.client.response.OutdoorsySearchResult

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

class MainFragmentRvVh(parent: ViewGroup) :
    BaseVH<OutdoorsySearchResult>(parent, R.layout.li_search_result) {

    @BindView(R.id.search_result_image) lateinit var mItemImage: AppCompatImageView
    @BindView(R.id.search_result_title) lateinit var mItemTitle: AppCompatTextView

    override fun setData(item: OutdoorsySearchResult) {
        if (item.attributes?.primaryUrl?.isNotEmpty() == true) {
            Glide.with(mItemImage.context).load(item.attributes.primaryUrl).into(mItemImage)
            mItemImage.visibility = View.VISIBLE
        } else {
            mItemImage.visibility = View.INVISIBLE
        }
        mItemTitle.text = item.attributes?.name
    }
}