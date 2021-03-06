package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Resource
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) :

    BaseViewModel<CharactersViewEvent>() {

    var characters: MutableList<Character> = mutableListOf()

    var events = MutableLiveData<CharactersViewEvent>()

    fun fetch(name: String? = null, offset: Int) {
        async {
            getCharactersUseCase.request = GetCharactersRequest(name = name, offset = offset)
            getCharactersUseCase.execute({ response ->
                response?.characters?.data?.results?.map {
                    characters.add(it)
                }
                events.value = CharactersViewEvent.OnLoadMoreCharacters(characters)
            }, {
                onError(it?.message)
            })
        }
    }

    fun search(name: String? = null, offset: Int) {
        async {
            getCharactersUseCase.request =
                GetCharactersRequest(name = name, offset = offset)
            getCharactersUseCase.execute({ response ->
                response?.characters?.data?.results?.map {
                    characters.add(it)
                }
                events.value = CharactersViewEvent.OnLoadSearch(response?.characters)
            }, {
                onError(it?.message)
            })
        }
    }

    fun getFavoriteStatus(id: Int, onResponse: (Boolean?) -> Unit) {
        async {
            getFavoriteUseCase.request = GetFavoriteRequest(id)
            getFavoriteUseCase.execute({ response ->
                onResponse(response?.isFavorite)
            }, {
                onError(it?.message)
            })
        }
    }

    fun updateFavorite(character: Character) {
        async {
            updateFavoriteUseCase.request = UpdateFavoriteRequest(character)
            updateFavoriteUseCase.execute({ response ->
                events.value = response?.character?.let { character ->
                    CharactersViewEvent.OnFavoriteUpdated(character)
                }
            }, {
                onError(it?.message)
            })
        }
    }

    fun getFavorites() {
        async {
            getFavoritesUseCase.execute({ response ->
                events.value = CharactersViewEvent.OnLoadFavorites(response?.characters)
            }, { error ->
                onError(error?.message)
            })
        }
    }

    override fun onError(message: String?) {
        events.value = CharactersViewEvent.OnError(message)
    }
}