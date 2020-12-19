package com.aleson.marvel.marvelcharacters.feature.character.view.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.viewholder.CharacterViewHolder

private const val COLUMNS = 2
private const val DIRECTION = 2
private const val IDLE = 0

class CharactersWidget(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var recyclerView: RecyclerView
    private var swipeRefresh: SwipeRefreshLayout
    private var errorLayout: ConstraintLayout
    private var emptyLayout: ConstraintLayout
    private var loadingMore = false

    lateinit var onItemSelected: (Character) -> Unit
    lateinit var onFavorite: (Character) -> Unit
    lateinit var onRefresh: () -> Unit
    var onLoadMore: () -> Unit = {}

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
        swipeRefresh = view.findViewById(R.id.characters_swipe_refresh)
        errorLayout = view.findViewById(R.id.error_layout_container)
        emptyLayout = view.findViewById(R.id.empty_layout_container)
        recyclerView.layoutManager = GridLayoutManager(context, COLUMNS)
        recyclerView.adapter = charactersAdapter
        charactersAdapter.clear()
        errorLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
        items.clear()
        onSwipeListener()
        onScrollListener()
    }

    fun addAll(
        characters: List<Character>?
    ) {
        clearExceptionStates()
        charactersAdapter.clear()
        characters?.forEach { character -> items.add(ViewItem(character)) }
        charactersAdapter.add(items)
        notifyDataChange()
        loadingMore = false
    }

    fun updateFavoriteItem(character: Character) {
        items.map { itemView ->
            if (itemView.data.id == character.id) {
                itemView.data.favorite = character.favorite
            }
        }
        charactersAdapter.notifyDataSetChanged()
    }

    fun notifyDataChange() {
        charactersAdapter.notifyDataSetChanged()
    }

    private fun onSwipeListener() {
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            onRefresh()
        }
    }

    private fun onScrollListener() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(DIRECTION) && dy != IDLE && !loadingMore && items.isEmpty()
                ) {
                    loadingMore = true
                    onLoadMore()
                }
            }
        })
    }

    fun isEmpty() = items.isEmpty()

    fun getItems() = items

    fun reset() {
        items.clear()
        charactersAdapter.clear()
        notifyDataChange()
    }

    fun onError() {
        reset()
        errorLayout.visibility = View.VISIBLE
    }

    fun onEmpty() {
        reset()
        emptyLayout.visibility = View.VISIBLE
    }

    private fun clearExceptionStates() {
        errorLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
    }

}