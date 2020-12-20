package com.aleson.marvel.marvelcharacters.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<T> = mutableListOf()
    private lateinit var viewHolder: RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenericBinder<T>).bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, items[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    fun clear() {
        items.clear()
    }

    fun add(newItems: List<T>) {
        addAndNotify(newItems)
    }

    fun add(newItem: T) {
        items.add(newItem)
        this.notifyDataSetChanged()
    }

    private fun addAndNotify(newItems: List<T>) {
        if (items.isEmpty()) {
            items.addAll(newItems)
        } else {
            items.addAll(items.size, newItems)
        }
        this.notifyDataSetChanged()
    }
}

interface GenericBinder<T> {
    fun bind(data: T, position: Int)
}