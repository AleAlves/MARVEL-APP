package com.aleson.marvel.marvelcharacters.feature.character.view.ui.fragment

import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.base.BaseFragment
import com.aleson.marvel.marvelcharacters.core.extension.offsetSchema
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.di.CharactersInjector
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.character.view.ui.widget.CharactersWidget
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel


class CharactersFragment : BaseFragment() {

    private var offset: Int = 0
    private lateinit var viewModel: CharactersViewModel
    private lateinit var charactersWidget: CharactersWidget
    private lateinit var characterSearch: EditText

    override fun getFragmentTag() = "CharactersFragment"

    override fun getFragmentLayout() = R.layout.fragment_characters

    override fun onBindView(view: View) {
        charactersWidget = view.findViewById(R.id.characters_view_recyclerview)
        characterSearch = view.findViewById(R.id.characters_edittext_search)
    }

    override fun setupView() {
        offset = 0
        characterSearch.inputType = InputType.TYPE_CLASS_TEXT
        if (charactersWidget.getItems().isEmpty()) {
            charactersWidget.reset()
            fetch()
        }
    }

    override fun setupViewModel() {
        this.viewModel = ViewModelProviders.of(
            this, CharactersInjector.provideCharactersViewModelFactory(activity?.applicationContext)
        ).get(CharactersViewModel::class.java)
    }

    override fun onClickListeners() {
        charactersWidget.onItemSelected = {
            setNavigationController(it)
        }

        charactersWidget.onFavorite = {
            updateFavorite(it)
        }

        charactersWidget.onRefresh = {
            refresh()
        }

        charactersWidget.onLoadMore = {
            offsetSchema(offset) { offset ->
                this.offset = offset
                fetch()
            }
        }

        characterSearch.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == 5) {
                offset = 0
                search()
                handled = true
            }
            handled
        }
    }

    private fun search() {
        super.showLoading()
        charactersWidget.reset()
        viewModel.search(name = characterSearch.text.toString(), offset = offset)
    }

    private fun fetch() {
        super.showLoading()
        viewModel.fetch(name = characterSearch.text.toString(), offset = offset)
    }

    private fun refresh() {
        super.showLoading()
        offset = 0
        charactersWidget.reset()
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
                is CharactersViewEvent.OnFavoriteUpdated -> charactersWidget.updateFavoriteItem(it.character)
                is CharactersViewEvent.OnError -> {
                    onError(it.error)
                }
                else -> showToast(context, "something went wrong")
            }
        })
    }

    private fun onError(message: String?) {
        super.showToast(context, message)
        if (charactersWidget.getItemsCount() == 0) {
            charactersWidget.onError()
        }
    }

    private fun onLoadMoreCharacters(characters: ArrayList<Character>?) {
        charactersWidget.addAll(characters)
        onBindFavorites()
        super.hideLoading()
    }

    private fun onLoadSearchResult(characters: ArrayList<Character>?) {
        super.hideLoading()
        charactersWidget.addAll(characters)
        onBindFavorites()
    }

    private fun onBindFavorites() {
        charactersWidget.getItems().map { itemView ->
            viewModel.getFavoriteStatus(itemView.data.id) { isFavorite ->
                itemView.data.favorite = isFavorite as Boolean
                charactersWidget.notifyDataChange()
            }
        }
    }

    private fun updateFavorite(character: Character) {
        viewModel.updateFavorite(character)
    }

    private fun setNavigationController(character: Character) {
        val bundle = bundleOf("character" to character)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

}