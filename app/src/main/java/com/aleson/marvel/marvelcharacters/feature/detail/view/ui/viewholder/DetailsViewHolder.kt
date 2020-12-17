package com.aleson.marvel.marvelcharacters.feature.detail.view.ui.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.ui.GenericBinder
import com.aleson.marvel.marvelcharacters.core.extension.loadImageFromUrl
import com.aleson.marvel.marvelcharacters.core.model.character.Resource
import com.aleson.marvel.marvelcharacters.core.model.character.Image

class DetailsViewHolder(
    var context: Context?,
    view: View
) :
    RecyclerView.ViewHolder(view), GenericBinder<ViewItem<Resource>> {

    private val name: TextView = view.findViewById(R.id.character_details_textview_name)
    private val image: ImageView = view.findViewById(R.id.character_details_imageview_image)

    override fun bind(data: ViewItem<Resource>, position: Int) {
        name.text = data.data.name
        loadImageFromUrl(
            context as Context,
            Image(
                path = data.data.image?.path,
                extension = data.data.image?.extension
            ),
            image,
            R.drawable.comics_placeholder
        )
    }
}