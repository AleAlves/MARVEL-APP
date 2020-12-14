package com.aleson.marvel.marvelcharacters.feature.character.repository

import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource

class CharactersRepository(private val source: CharactersDataSource) : CharactersDataSource {

    override fun getCharacters(onResponse: (CharacterDataWrapper) -> Unit, onError: () -> Unit) {
        source.getCharacters(onResponse, onError)
    }
}