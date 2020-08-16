package com.outdoorsy.testapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.outdoorsy.testapp.R
import com.outdoorsy.testapp.client.response.OutdoorsySearchResult
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

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainFragmentListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainFragmentListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = adapter

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

        viewModel.searchStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MainViewModel.SearchStatus.Loaded -> {
                    hideProgress()
                    mEmptyListText.visibility = View.GONE
                    if (it.result.isEmpty()) {
                        mEmptyListText.visibility = View.VISIBLE
                    } else {
                        mEmptyListText.visibility = View.GONE
                    }
                    adapter.submitList(it.result)
                }
                is MainViewModel.SearchStatus.Error -> {
                    hideProgress()
                    Toast.makeText(requireActivity(), it.error, Toast.LENGTH_LONG).show()
                }
                is MainViewModel.SearchStatus.Loading -> {
                    showProgress()
                }
            }
        })
    }

    private fun searchOutdoorsy(text: String?) {
        if (text?.length!! > 1) {
            viewModel.search(text?.trim())
        } else {
            adapter.submitList(ArrayList<OutdoorsySearchResult>())
        }
    }
}