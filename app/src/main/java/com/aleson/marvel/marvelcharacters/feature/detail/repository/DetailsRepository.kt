package com.aleson.marvel.marvelcharacters.feature.detail.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteResponse
import com.aleson.marvel.marvelcharacters.feature.detail.repository.data.DetailsDataSource
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.*

class DetailsRepository(private var source: DetailsDataSource) : DetailsDataSource {

    override fun getComicsMedia(
        request: GetComicsMediaRequest,
        onResponse: (GetComicsMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getComicsMedia(request, onResponse, onError)
    }

    override fun getSeriesMedia(
        request: GetSeriesMediaRequest,
        onResponse: (GetSeriesMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getSeriesMedia(request, onResponse, onError)
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.updateFavorite(request, onResponse, onError)
    }

}