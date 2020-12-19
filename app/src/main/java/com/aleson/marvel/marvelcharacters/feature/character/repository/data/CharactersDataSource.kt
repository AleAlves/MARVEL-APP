package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*

interface CharactersDataSource : UpdateDataSource {

    fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (GetCharactersResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )

    fun getFavoriteStatus(
        request: GetFavoriteRequest,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )

    fun getFavorites(onResponse: (GetFavoritesResponse) -> Unit, onError: (ErrorModel) -> Unit)
}