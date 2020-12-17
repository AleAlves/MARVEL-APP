package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.custom.CharactersView
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel

class CharactersFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var charactersView: CharactersView

    override fun getFragmentTag() = "CharactersFragment"

    override fun getFragmentLayout() = R.layout.fragment_characters

    override fun onBindView(view: View) {
        charactersView = view.findViewById(R.id.characters_view_recyclerview)
    }

    override fun setupView() {}

    override fun setupViewModel() {
        super.showLoading()
        this.viewModel = ViewModelProviders.of(
            this,
            CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)

        viewModel.getCharacters()
    }

    override fun onClickListeners() {
        charactersView.onItemSelected = {
            setNavigationController(it)
        }

        charactersView.onFavorite = {  updateFavorite(it)
        }
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            super.hideLoading()
            when (it) {
                is CharactersViewEvent.OnLoadCharacters -> {
                    charactersView.addAll(it.characters?.data?.results)
                    onLoadFavorites(it.characters?.data?.results)
                }
                is CharactersViewEvent.OnFavoriteUpdated -> charactersView.updateFavoriteItem(it.character)
                is CharactersViewEvent.OnError -> super.showToast(context, it.error)
            }
        })
    }

    private fun onLoadFavorites(results: ArrayList<Character>?) {
        results?.map { character ->
            viewModel.getFavoriteStatus(character.id) { isFavorite ->
                character.favorite = isFavorite as Boolean
                charactersView.notifyDataChange()
            }
        }
    }

    private fun updateFavorite(character: Character) {
        viewModel.updateFavorite(character)
    }

    private fun setNavigationController(character: Character) {
        val bundle = bundleOf("character" to character)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_home_destination_to_detailFragment, bundle)
    }

}