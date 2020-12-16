package com.aleson.marvel.marvelcharacters.feature.character.repository

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*

class CharactersRepository(private val source: CharactersDataSource) : CharactersDataSource {

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (CharacterDataWrapper) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getCharacters(request, onResponse, onError)
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.updateFavorite(request, onResponse, onError)
    }

    override fun getFavoriteStatus(
        request: GetFavoriteRequest,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getFavoriteStatus(request, onResponse, onError)
    }

    override fun getFavorites(
        onResponse: (GetFavoritesResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getFavorites(onResponse, onError)
    }
}