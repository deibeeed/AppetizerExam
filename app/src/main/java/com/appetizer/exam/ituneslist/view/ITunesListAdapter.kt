package com.appetizer.exam.ituneslist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.appetizer.exam.R
import com.appetizer.exam.core.view.BaseRecyclerViewAdapter
import com.appetizer.exam.databinding.ItunesItemBinding
import com.appetizer.exam.entities.ITunesEntity

/**
 * Created by: david on 2019-02-21
 * All rights reserved.
 */
class ITunesListAdapter(private val context: Context): BaseRecyclerViewAdapter<ITunesEntity, ITunesItemViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITunesItemViewHolder {
        val binding = DataBindingUtil.inflate<ItunesItemBinding>(inflater, R.layout.itunes_item, parent, false)

        return ITunesItemViewHolder(binding)
    }
}