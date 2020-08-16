package com.outdoorsy.testapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.outdoorsy.testapp.R
import com.outdoorsy.testapp.ui.BaseFragment
import com.outdoorsy.testapp.ui.adapters.MainFragmentListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainFragment : BaseFragment() {

    @BindView(R.id.fragment_list) lateinit var mRecyclerView: RecyclerView
    @BindView(R.id.fragment_empty_text) lateinit var mEmptyListText: TextView
    @BindView(R.id.fragment_search_edit) lateinit var mSearch: EditText

    companion object {
        const val TEXT_WATCHER_TIMEOUT: Long = 500

        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
//    private lateinit var adapter: MainFragmentListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        adapter = MainFragmentListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        mRecyclerView.adapter = adapter

        addDisposable(
            RxTextView.afterTextChangeEvents(mSearch)
            .skipInitialValue()
            .debounce(TEXT_WATCHER_TIMEOUT, TimeUnit.MILLISECONDS)
            .map { textViewAfterTextChangeEvent -> textViewAfterTextChangeEvent.view().text.toString() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text -> searchOutdoorsy(text) },  {
                System.out.print(it)
            }))

        mSearch.post({ mSearch.requestFocus()})

        // TODO: Observe search result
    }

    private fun searchOutdoorsy(text: String?) {
        // TOTO: search ...
    }
}