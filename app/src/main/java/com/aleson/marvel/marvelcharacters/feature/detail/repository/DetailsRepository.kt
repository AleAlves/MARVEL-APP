package com.aleson.marvel.marvelcharacters.feature.detail.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.detail.repository.data.DetailsDataSource
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaResponse
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetSeriesMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetSeriesMediaResponse

class DetailsRepository(private var source: DetailsDataSource) :
    DetailsDataSource {

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
}