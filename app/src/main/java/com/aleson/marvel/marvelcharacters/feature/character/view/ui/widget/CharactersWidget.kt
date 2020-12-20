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
import com.aleson.marvel.marvelcharacters.core.extension.position
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
    private var emptySearchLayout: ConstraintLayout
    private var loadingMore = false

    lateinit var onItemSelected: (Character) -> Unit
    lateinit var onFavorite: (Character) -> Unit
    lateinit var onRefresh: () -> Unit
    var onLoadMore: () -> Unit = {}

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
        emptySearchLayout = view.findViewById(R.id.empty_search_layout_container)
        recyclerView.layoutManager = GridLayoutManager(context, COLUMNS)
        recyclerView.adapter = charactersAdapter
        charactersAdapter.items.clear()
        charactersAdapter.clear()
        errorLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
        emptySearchLayout.visibility = View.GONE
        onSwipeListener()
        onScrollListener()
    }

    fun addAll(
        characters: List<Character>?
    ) {
        charactersAdapter.clear()
        charactersAdapter.items.clear()
        characters?.forEach { character -> charactersAdapter.add(ViewItem(character)) }
        notifyDataChange()
        if (loadingMore) {
            scrollToPosition(position(charactersAdapter.items.size))
        }
        loadingMore = false
    }

    fun updateFavoriteItem(character: Character) {
        charactersAdapter.items.map { itemView ->
            if (itemView.data.id == character.id) {
                itemView.data.favorite = character.favorite
            }
        }
        notifyDataChange()
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
                if (!recyclerView.canScrollVertically(DIRECTION) && dy != IDLE && !loadingMore && charactersAdapter.items.isNotEmpty()
                ) {
                    loadingMore = true
                    onLoadMore()
                }
            }
        })
    }

    fun isEmpty() = charactersAdapter.items.isEmpty()

    fun getItems() = charactersAdapter.items

    fun reset() {
        charactersAdapter.items.clear()
        charactersAdapter.clear()
        clearExceptionStates()
        notifyDataChange()
    }

    fun onError() {
        reset()
        errorLayout.visibility = View.VISIBLE
    }

    fun onEmptySearch() {
        reset()
        emptySearchLayout.visibility = View.VISIBLE
    }

    fun onEmpty() {
        reset()
        emptyLayout.visibility = View.VISIBLE
    }

    private fun clearExceptionStates() {
        errorLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
        emptySearchLayout.visibility = View.GONE
    }

    fun scrollToPosition(position: Int) {
        recyclerView.smoothScrollToPosition(position)
    }

}