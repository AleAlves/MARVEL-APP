package com.aleson.marvel.marvelcharacters.feature.favorite.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.favorite.usecase.GetavoritesResponse

interface FavoritesDataSource {

    fun getFavorites(onResponse: (GetavoritesResponse) -> Unit, onError: (ErrorModel) -> Unit)

}