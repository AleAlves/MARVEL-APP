package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveFavoriteUseCase: UpdateFavoriteUseCase,
    private val getFavoriteResponse: GetFavoriteUseCase
) :

    BaseViewModel<CharactersViewEvent>() {

    override fun setup() {
        getCharacters()
    }

    private fun getCharacters() {
        CoroutineScope(coroutineContext).launch {
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
            getFavoriteResponse.request = GetFavoriteRequest(id)
            getFavoriteResponse.execute({ response ->
                onResponse(response?.isFavorite)
            }, {
                onError(it?.message)
            })
        }
    }

    fun updateFavorite(character: Character) {
        async {
            saveFavoriteUseCase.request = UpdateFavoriteRequest(character)
            saveFavoriteUseCase.execute({ response ->
                super.events.value = response?.character?.let { character ->
                    CharactersViewEvent.OnFavoriteSaved(character)
                }
            }, {
                onError(it?.message)
            })
        }
    }

    override fun onError(message: String?) {
        super.events.value = CharactersViewEvent.OnError(message)
    }
}