package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteUseCase
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
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

    fun saveFavorite(character: Character) {
        async {
            saveFavoriteUseCase.request = SaveFavoriteRequest(character)
            saveFavoriteUseCase.execute({
                print("")
            }, {
                onError(it?.message)
            })
        }
    }

    override fun onError(message: String?) {
        super.events.value = CharactersViewEvent.OnError(message)
    }
}