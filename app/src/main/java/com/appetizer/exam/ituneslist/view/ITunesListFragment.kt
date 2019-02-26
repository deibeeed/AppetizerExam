package com.appetizer.exam.ituneslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.appetizer.exam.R
import com.appetizer.exam.core.BaseFragment
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.entities.ITunesEntity
import com.appetizer.exam.ituneslist.viewmodel.ITunesListViewModel
import com.appetizer.exam.ituneslist.viewmodel.ITunesViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import kotlinx.android.synthetic.main.itunes_list_fragment.*
import timber.log.Timber


class ITunesListFragment : BaseFragment() {

    private lateinit var viewModel: ITunesListViewModel
    private var adapter: ITunesListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.itunes_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeRecyclerView()

        viewModel = ViewModelProviders
                .of(this, ITunesViewModelFactory(coreComponent = coreComponent))
                .get(ITunesListViewModel::class.java)

//        val disposable = viewModel
//                .load()
//                .subscribe({
//                    adapter?.set(it)
//                }, {
//                    Timber.e("An error occured while getting itunes items")
//                    it.printStackTrace()
//                }, {
//                    Timber.i("itunes load complete")
//                })

        viewModel.itunes.observe(this, Observer<DataWrapper<List<ITunesEntity>>> {
            it.error?.let {err ->
                Snackbar
                        .make(rootContainer, err.message as CharSequence, LENGTH_SHORT)
                        .show()
                return@Observer
            }

            it.data?.let { data ->
                adapter?.set(data)
            }
        })

        viewModel.lastView.observe(this, Observer {
            tvDate.text = "Last Access: ${it.data}"
        })

        viewModel.updateLastView()
    }

    private fun initializeRecyclerView() {
        val layoutManager = GridLayoutManager(activity, 1)

        if (adapter == null) {
            adapter = ITunesListAdapter(activity!!)
        }

        rvItunes.layoutManager = layoutManager
        rvItunes.adapter = adapter
        rvItunes.addItemDecoration(DividerItemDecoration(activity, VERTICAL))
    }
}
