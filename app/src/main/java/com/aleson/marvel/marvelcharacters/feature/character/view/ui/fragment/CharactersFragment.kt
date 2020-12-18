package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.extension.generateOffset
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.custom.CharactersView
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel


class CharactersFragment : BaseFragment() {

    private var offset: Int = 0
    private lateinit var viewModel: CharactersViewModel
    private lateinit var charactersView: CharactersView
    private lateinit var characterSearch: EditText

    override fun getFragmentTag() = "CharactersFragment"

    override fun getFragmentLayout() = R.layout.fragment_characters

    override fun onBindView(view: View) {
        charactersView = view.findViewById(R.id.characters_view_recyclerview)
        characterSearch = view.findViewById(R.id.characters_edittext_search)
    }

    override fun setupView() {
        offset = 0
        characterSearch.inputType = InputType.TYPE_CLASS_TEXT
        fetch()
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(
            this, CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)
    }

    override fun onClickListeners() {
        charactersView.onItemSelected = {
            listener.onDetails(it)
        }

        charactersView.onFavorite = {
            updateFavorite(it)
        }

        charactersView.onRefresh = {
            refresh()
        }

        charactersView.onLoadMore = {
            if (characterSearch.text.isNullOrEmpty()) {
                generateOffset(offset) { offset ->
                    this.offset = offset
                    fetch()
                }
            }
        }

        characterSearch.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == 5) {
                offset = 0
                charactersView.reset()
                search()
                handled = true
            }
            handled
        }
    }

    private fun search() {
        super.showLoading()
        viewModel.search(name = characterSearch.text.toString(), offset = offset)
    }

    private fun fetch() {
        super.showLoading()
        viewModel.fetch(name = characterSearch.text.toString(), offset = offset)
    }

    private fun refresh() {
        super.showLoading()
        offset = 0
        charactersView.reset()
        characterSearch.text.clear()
        viewModel.fetch()
    }

    override fun oberserverEvent() {
        this.viewModel.events.observe(this, Observer {
            when (it) {
                is CharactersViewEvent.OnLoadSearch -> {
                    onLoadSearchResult(it.characters?.data?.results)
                }
                is CharactersViewEvent.OnLoadMoreCharacters -> {
                    onLoadMoreCharacters(it.characters?.data?.results)
                }
                is CharactersViewEvent.OnFavoriteUpdated -> charactersView.updateFavoriteItem(it.character)
                is CharactersViewEvent.OnError -> super.showToast(context, it.error)
                else -> showToast(context, "something went wrong")
            }
        })
    }

    private fun onLoadMoreCharacters(characters: ArrayList<Character>?) {
        charactersView.addAll(characters)
        super.hideLoading()
    }

    private fun onLoadSearchResult(characters: ArrayList<Character>?) {
        super.hideLoading()
        charactersView.addAll(characters)
        onLoadFavorites(characters)
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
}