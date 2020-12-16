package com.aleson.marvel.marvelcharacters.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listItems: MutableList<T>
    private lateinit var viewHolder: RecyclerView.ViewHolder

    init {
        listItems = mutableListOf()
    }

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
        (holder as GenericBinder<T>).bind(listItems[position], position)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    /**
     * Abstract function to set layout id in viewholder
     *
     * @param position : item position
     * @param obj: Generic any type of data.
     */
    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    fun clear() {
        listItems = mutableListOf()
    }

    fun add(newItems: List<T>) {
        addAndNotify(newItems)
    }

    fun add(newItem: T) {
        addAndNotify(mutableListOf(newItem))
    }

    private fun addAndNotify(newItems: List<T>) {
        if (listItems.isEmpty()) {
            listItems.addAll(newItems)
        } else {
            listItems.addAll(listItems.size, newItems)
        }
        this.notifyDataSetChanged()
    }
}

interface BaseRecyclerListener<T> {

    fun onClickListener(data: T, v: View, position: Int)
}

interface GenericBinder<T> {
    fun bind(data: T, position: Int)
}