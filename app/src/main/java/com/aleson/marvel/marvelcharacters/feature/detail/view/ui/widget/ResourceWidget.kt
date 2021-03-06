package com.aleson.marvel.marvelcharacters.feature.detail.view.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.model.character.Resource
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.feature.detail.view.ui.viewholder.DetailsViewHolder

class ResourceWidget(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var title: TextView
    private var recyclerView: RecyclerView

    private var view: View = LayoutInflater.from(context).inflate(
        R.layout.resource_list_view, this, true
    )

    private var adapter: BaseRecyclerViewAdapter<ViewItem<Resource>> =
        object : BaseRecyclerViewAdapter<ViewItem<Resource>>() {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                DetailsViewHolder(context, view)

            override fun getLayoutId(position: Int, obj: ViewItem<Resource>) =
                R.layout.viewholder_media
        }

    init {
        title = view.findViewById(R.id.resource_textview_label)
        recyclerView = view.findViewById(R.id.resource_recyclerview)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun addAll(
        resources: List<Resource>?
    ) {
        adapter.items.clear()
        adapter.clear()
        resources?.map { resource -> adapter.items.add(ViewItem(resource)) }
        emptyState()
    }

    private fun emptyState() {
        if (adapter.items.isEmpty()) {
            title.visibility = View.GONE
        }
    }

    fun notifyDataChange() {
        adapter.notifyDataSetChanged()
    }
}