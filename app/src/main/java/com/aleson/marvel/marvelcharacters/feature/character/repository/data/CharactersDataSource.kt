package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper

interface CharactersDataSource {

    fun getCharacters(onResponse: (CharacterDataWrapper) -> Unit, onError: () -> Unit)

}