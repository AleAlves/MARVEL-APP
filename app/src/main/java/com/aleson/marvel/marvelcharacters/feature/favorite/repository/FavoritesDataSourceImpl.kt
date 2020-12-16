package com.aleson.marvel.marvelcharacters.feature.favorite.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.dao.AppDatabase
import com.aleson.marvel.marvelcharacters.feature.favorite.usecase.GetavoritesResponse

class FavoritesDataSourceImpl(var database: AppDatabase?) : FavoritesDataSource {

    override fun getFavorites(
        onResponse: (GetavoritesResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        val characters = database?.favorites()?.getAll()
        characters?.let { GetavoritesResponse(it) }?.let { onResponse(it) }
    }
}