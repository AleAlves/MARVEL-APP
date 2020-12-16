package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteResponse

interface UpdateDataSource {

    fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )
}