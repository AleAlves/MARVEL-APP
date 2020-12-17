package com.aleson.marvel.marvelcharacters.feature.character.view.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.viewholder.CharacterViewHolder

class CharactersView(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var recyclerView: RecyclerView
    lateinit var onItemSelected: (Character) -> Unit
    lateinit var onFavorite: (Character) -> Unit
    private val items: MutableList<ViewItem<Character>> = mutableListOf()

    private var view: View = LayoutInflater.from(context).inflate(
        R.layout.character_list_view,
        this,
        true
    )

    private var charactersAdapter: BaseRecyclerViewAdapter<ViewItem<Character>> =
        object : BaseRecyclerViewAdapter<ViewItem<Character>>() {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                CharacterViewHolder<ViewItem<Character>>(
                    context,
                    view,
                    onItemSelected = { onItemSelected(it) },
                    onFavorite = { onFavorite(it) })

            override fun getLayoutId(position: Int, obj: ViewItem<Character>) =
                R.layout.viewholder_character
        }

    init {
        recyclerView = view.findViewById(R.id.characters_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = charactersAdapter
    }

    fun addAll(
        characters: List<Character>?
    ) {
        items.clear()
        charactersAdapter.clear()
        characters?.forEach { character  -> items.add(ViewItem(character)) }
        charactersAdapter.add(items)
    }

    fun updateFavoriteItem(character: Character) {
        items.map { itemView ->
            if(itemView.data.id == character.id){
                itemView.data.favorite = character.favorite
            }
        }
        charactersAdapter.notifyDataSetChanged()
    }

    fun notifyDataChange() {
        charactersAdapter.notifyDataSetChanged()
    }
}