package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.custom.CharactersView
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel

    private lateinit var favorites: CharactersView

    override fun getFragmentTag() = "FavoritesFragment"

    override fun getFragmentLayout() = R.layout.fragment_favorites

    override fun onBindView(view: View) {
        favorites = view.findViewById(R.id.characters_view_recyclerview)
    }

    override fun setupView() {}

    override fun setupViewModel() {

        super.showLoading()

        viewModel = ViewModelProviders.of(
            this,
            CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)

        viewModel.getFavorites()
    }

    override fun onClickListeners() {
        favorites.onItemSelected = {
            listener.onDetails(it)
        }

        favorites.onFavorite = {
            listener.onDetails(it)
        }

        favorites.onRefresh = {
            super.showLoading()
            viewModel.getFavorites()
        }
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            super.hideLoading()
            when (it) {
                is CharactersViewEvent.OnLoadFavorites -> it.characters?.let { characters ->
                    loadFavorites(characters)
                }
                is CharactersViewEvent.OnError -> {
                    onError(it.error)
                }
                else -> showToast(context, "something weird happned!")
            }
        })
    }

    private fun onError(message: String?) {
        favorites.onError()
        super.showToast(context, message.toString())
    }

    private fun loadFavorites(characters: List<Character>) {
        favorites.reset()
        if (characters.isEmpty()) {
            favorites.onEmpty()
        } else {
            favorites.addAll(characters)
        }
    }

}