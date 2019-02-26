package com.appetizer.exam.itunesdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appetizer.exam.R
import com.appetizer.exam.core.BaseFragment
import com.appetizer.exam.databinding.ItunesDetailFragmentBinding
import com.appetizer.exam.itunesdetail.viewmodel.ITunesDetailViewModel
import com.appetizer.exam.itunesdetail.viewmodel.ITunesDetailViewModelFactory


class ITunesDetailFragment : BaseFragment() {

    private lateinit var viewModel: ITunesDetailViewModel
    private lateinit var binding: ItunesDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.itunes_detail_fragment,
                container, false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
                .of(this, ITunesDetailViewModelFactory(coreComponent = coreComponent))
                .get(ITunesDetailViewModel::class.java)

        arguments?.let {
            val itunesEnity = ITunesDetailFragmentArgs
                    .fromBundle(it)
                    .entity

            binding.itunes = itunesEnity
            viewModel.saveLastScreen(itunesEnity)
        }

        viewModel.itunes.observe(this, Observer {
            it.error?.let { err ->
                Toast.makeText(activity, err.message, LENGTH_SHORT).show()
                return@Observer
            }

            it.data?.let {data ->
                binding.itunes = data
                viewModel.saveLastScreen(data)
            }
        })

        viewModel.updateLastView()
    }
}
