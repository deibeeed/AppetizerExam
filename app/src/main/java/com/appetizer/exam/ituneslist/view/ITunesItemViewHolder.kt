package com.appetizer.exam.ituneslist.view

import androidx.navigation.findNavController
import com.appetizer.exam.core.view.BaseViewHolder
import com.appetizer.exam.databinding.ItunesItemBinding
import com.appetizer.exam.entities.ITunesEntity
import kotlinx.android.synthetic.main.itunes_item.view.*

/**
 * Created by: david on 2019-02-21
 * All rights reserved.
 */
class ITunesItemViewHolder(private val binding: ItunesItemBinding): BaseViewHolder<ITunesEntity>(binding.root) {
    override fun bind(data: ITunesEntity) {
        binding.itunes = data
        itemView.sdv.setImageURI(data.artworkUrl100)

        itemView.setOnClickListener {
            val direction = ITunesListFragmentDirections.actionITunesListFragmentToITunesDetailFragment(
                    entity = data
            )

            it.findNavController().navigate(direction)
        }
    }

    override fun recycle() {
        itemView.sdv.setImageURI("")
        itemView.tvGenre.text = ""
        itemView.tvPrice.text = ""
        itemView.tvTrackName.text = ""
    }
}