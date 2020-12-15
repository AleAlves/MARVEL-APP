package com.aleson.marvel.marvelcharacters.feature.character.repository

import com.aleson.marvel.marvelcharacters.feature.character.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.model.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteRequest

class CharactersRepository(private val source: CharactersDataSource) : CharactersDataSource {

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (CharacterDataWrapper) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getCharacters(request, onResponse, onError)
    }

    override fun saveFavorite(
        request: SaveFavoriteRequest,
        onResponse: (Character) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.saveFavorite(request, onResponse, onError)
    }
}