package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.ui.BaseRecyclerViewAdapter
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.viewholder.CharacterViewHolder
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel

    private lateinit var recyclerView: RecyclerView

    override fun getFragmentTag() = "FavoritesFragment"

    override fun getFragmentLayout() = R.layout.fragment_favorites

    override fun onBindView(view: View) {
        recyclerView = view.findViewById(R.id.characters_recyclerview)
    }

    override fun setupView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    override fun setupViewModel() {

        super.showLoading()

        viewModel = ViewModelProviders.of(
            this,
            CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)

        viewModel.getFavorites()
    }

    override fun onClickListeners() {
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            when (it) {
                is CharactersViewEvent.OnLoadFavorites -> loadFavorites(it.characters)
                is CharactersViewEvent.OnError -> super.showToast(context, it.toString())
            }
        })
    }

    fun setNavigationController(character: Character) {
        val bundle = bundleOf("character" to character)
        findNavController(this).navigate(R.id.action_home_destination_to_detailFragment, bundle)
    }

    private fun loadFavorites(list: List<Character>?) {
        super.hideLoading()
        adapter.clear()
        list.let {
            it?.map { character ->
                adapter.add(character)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private var adapter: BaseRecyclerViewAdapter<Character> =
        object : BaseRecyclerViewAdapter<Character>() {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                CharacterViewHolder(context, view,
                    onItemSelected = { setNavigationController(it) },
                    onFavorite = { setNavigationController(it) })

            override fun getLayoutId(position: Int, obj: Character) = R.layout.viewholder_character
        }
}