package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteRequest

interface CharactersDataSource {

    fun getCharacters(request: GetCharactersRequest, onResponse: (CharacterDataWrapper) -> Unit, onError: (ErrorModel) -> Unit)

    fun saveFavorite(request: SaveFavoriteRequest, onResponse: (Character) -> Unit, onError: (ErrorModel) -> Unit)
}