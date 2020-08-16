package com.outdoorsy.testapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.outdoorsy.testapp.ui.ProgressActions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
* Created by Ergyun Syuleyman on 16.08.20.
* Copyright (c) 2020  . All rights reserved.
*/

abstract class BaseFragment  : Fragment() {

    protected var progressActions: ProgressActions? = null
    protected var mUnBinder: Unbinder? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    fun showProgress() {
        progressActions?.showProgress()
    }

    fun hideProgress() {
        progressActions?.hideProgress()
    }

    protected open fun addDisposable(disposable: Disposable?) {
        mCompositeDisposable!!.add(disposable!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mUnBinder != null) {
            mUnBinder?.unbind()
        }
        mUnBinder = ButterKnife.bind(this, view)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is ProgressActions) {
            progressActions = activity as ProgressActions
        }
    }

    override fun onDestroyView() {
        if (mCompositeDisposable != null && !mCompositeDisposable!!.isDisposed) mCompositeDisposable!!.clear()
        super.onDestroyView()
        if (mUnBinder != null) {
            mUnBinder!!.unbind()
            mUnBinder = null
        }
    }
}