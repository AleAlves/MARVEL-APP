package com.aleson.marvel.marvelcharacters.feature.character.view.ui.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.extension.loadImageFromUrl
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.ui.GenericBinder


class CharacterViewHolder<T>(
    var context: Context?,
    view: View,
    var onFavorite: (Character) -> Unit,
    var onItemSelected: (Character) -> Unit
) :
    RecyclerView.ViewHolder(view), GenericBinder<T> {

    private val name: TextView = view.findViewById(R.id.character_details_textview_name)
    private val image: ImageView = view.findViewById(R.id.character_details_imageview_image)
    private val button: ImageButton = view.findViewById(R.id.imageview_character_image_button)
    private val item: ConstraintLayout =
        view.findViewById(R.id.character_details_constraintlayout_item)

    override fun bind(data: T, position: Int) {
        (data as ViewItem<Character>)
        name.text = position.toString()
        loadImageFromUrl(context as Context, data.data.thumbnail, image, R.drawable.placeholder)
        item.setOnClickListener {
            onItemSelected(data.data)
        }
        button.setOnClickListener {
            it.isEnabled = false
            onFavorite(data.data)
        }
        if (data.data.favorite) {
            button.setImageDrawable((context as Context).getDrawable(R.drawable.ic_baseline_favorite_24_red))
        } else {
            button.setImageDrawable((context as Context).getDrawable(R.drawable.ic_baseline_favorite_border_24_black))
        }
    }
}