package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) :

    BaseViewModel<CharactersViewEvent>() {

    override fun setup() {
    }

    fun getCharacters() {
        async {
            getCharactersUseCase.request = GetCharactersRequest(limite = "20", orderBy = "name")
            getCharactersUseCase.execute({ response ->
                super.events.value = CharactersViewEvent.OnLoadCharacters(response?.characters)
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
                super.events.value = response?.character?.let { character ->
                    CharactersViewEvent.OnFavoriteSaved(character)
                }
            }, {
                onError(it?.message)
            })
        }
    }

    fun getFavorites() {
        async {
            getFavoritesUseCase.execute({ response ->
                super.events.value = CharactersViewEvent.OnLoadFavorites(response?.characters)
            }, { error ->
                onError(error?.message)
            })
        }
    }

    override fun onError(message: String?) {
        super.events.value = CharactersViewEvent.OnError(message)
    }
}