package com.appetizer.exam.core.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appetizer.exam.entities.BaseEntity

/**
 * Created by: david on 28/01/2019
 * All rights reserved.
 */
abstract class BaseViewHolder<T: BaseEntity>(itemView: View): RecyclerView.ViewHolder(itemView) {
    /**
     * binds the data to the View
     */
    abstract fun bind(data: T)

    /**
     * called when the view is not shown in the list anymore
     * resets the ViewHolder.
     */
    abstract fun recycle()
}