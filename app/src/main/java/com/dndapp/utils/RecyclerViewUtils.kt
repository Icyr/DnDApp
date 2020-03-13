package com.dndapp.utils

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface HasId<T> {
    val id: T
}

class GenericDiffCallback<T : HasId<K>, K>(
    private val old: List<T>,
    private val new: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = old[oldItemPosition]
        val newItem = new[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = old[oldItemPosition]
        val newItem = new[newItemPosition]
        return oldItem == newItem
    }
}

abstract class BindableViewHolder<K, T : HasId<K>>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}

abstract class GenericAdapter<K, T : HasId<K>, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var items: List<T> = emptyList()

    fun setItems(list: List<T>) {
        updateData {
            items = list
        }
    }

    private fun updateData(action: () -> Unit) {
        val oldItems = items
        action.invoke()
        val newItems = items
        val callback = GenericDiffCallback(oldItems, newItems)
        val res = DiffUtil.calculateDiff(callback)
        res.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        if (holder is BindableViewHolder<*, *>) {
            @Suppress("UNCHECKED_CAST")
            (holder as BindableViewHolder<K, T>).bind(item)
        }
    }
}