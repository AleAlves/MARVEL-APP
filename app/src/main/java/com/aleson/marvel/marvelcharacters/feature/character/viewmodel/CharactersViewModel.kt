package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent

class CharactersViewModel(
    private val getProductsUseCase: GetCharactersUseCase
) :

    BaseViewModel<CharactersViewEvent>() {

    override fun setup() {
        super.async {
            getCharacters()
        }
    }

    override fun onError() {
    }

    private fun getCharacters() {
        getProductsUseCase.execute { response ->
            super.events.value = CharactersViewEvent.OnLoadCharacters(response?.characters)
        }
    }
}