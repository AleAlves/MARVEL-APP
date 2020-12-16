package com.aleson.marvel.marvelcharacters.feature.detail

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.ui.GenericBinder
import com.aleson.marvel.marvelcharacters.core.util.loadImageFromUrl
import com.aleson.marvel.marvelcharacters.core.model.character.ComicsItem
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsMedia

class DetailsViewHolder(
    var context: Context?,
    view: View
) :
    RecyclerView.ViewHolder(view), GenericBinder<ComicsItem> {

    private val name: TextView = view.findViewById(R.id.character_details_textview_name)
    private val image: ImageView = view.findViewById(R.id.character_details_imageview_image)

    override fun bind(data: ComicsItem, position: Int) {
        name.text = data.name
        loadImageFromUrl(
            context as Context,
            Image(
                path = data.resourceURI,
                extension = "jpg"
            ),
            image,
            R.drawable.comics_placeholder
        )
    }
}