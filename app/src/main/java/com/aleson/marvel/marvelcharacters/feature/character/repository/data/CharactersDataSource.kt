package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*

interface CharactersDataSource {

    fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (CharacterDataWrapper) -> Unit,
        onError: (ErrorModel) -> Unit
    )

    fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )

    fun getFavoriteStatus(
        request: GetFavoriteRequest,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )
}