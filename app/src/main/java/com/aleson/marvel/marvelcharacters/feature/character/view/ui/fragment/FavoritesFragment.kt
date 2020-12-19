package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.widget.CharactersWidget
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

private const val ARG_CHARACTER = "character"

class FavoritesFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel

    private lateinit var favorites: CharactersWidget

    override fun getFragmentTag() = "FavoritesFragment"

    override fun getFragmentLayout() = R.layout.fragment_favorites

    override fun onBindView(view: View) {
        favorites = view.findViewById(R.id.characters_view_recyclerview)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
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
            setNavigationController(it)
        }

        favorites.onFavorite = {
            setNavigationController(it)
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
                else -> showToast(context, getString(R.string.label_generic_error_message))
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

    private fun setNavigationController(character: Character) {
        val bundle = bundleOf(ARG_CHARACTER to character)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

}