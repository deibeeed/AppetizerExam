package com.appetizer.exam.core.view

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.appetizer.exam.entities.BaseEntity

/**
 * Created by: david on 28/01/2019
 * All rights reserved.
 */
abstract class BaseRecyclerViewAdapter<T: BaseEntity, VH: BaseViewHolder<T>>: RecyclerView.Adapter<VH>() {
    private lateinit var data: List<T>

    /**
     * sets the data of the adapter
     */
    open fun set(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    /**
     * binds the data to the specific ViewHolder
     */
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    /**
     * gets the total item count
     */
    override fun getItemCount(): Int = if (::data.isInitialized) data.size else 0

    @CallSuper
    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

//    override fun getItemViewType(position: Int): Int = data[position].type
}