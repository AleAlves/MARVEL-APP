package com.aleson.marvel.marvelcharacters.feature.favorite.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.favorite.usecase.GetavoritesResponse

class FavoritesRepository(private val source: FavoritesDataSource) :
    FavoritesDataSource {

    override fun getFavorites(
        onResponse: (GetavoritesResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getFavorites(onResponse, onError)
    }
}