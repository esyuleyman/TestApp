package com.outdoorsy.testapp.ui.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.Unbinder

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

abstract class BaseVH<I>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context
    protected var mUnbinder: Unbinder? = null

    constructor(
        parent: ViewGroup,
        @LayoutRes layoutId: Int
    ) : this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
    }

    fun bind() {
        mUnbinder = ButterKnife.bind(this, itemView)
    }

    fun bind(item: I, position: Int) {
        if (mUnbinder != null) mUnbinder!!.unbind()
        mUnbinder = ButterKnife.bind(this, itemView)
        setData(item)
    }

    abstract fun setData(item: I)

    init {
        context = itemView.context
        bind()
    }
}
