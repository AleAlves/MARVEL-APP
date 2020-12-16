package com.aleson.marvel.marvelcharacters.feature.detail.repository.data

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaResponse
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetSeriesMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetSeriesMediaResponse

interface DetailsDataSource {

    fun getComicsMedia(
        request: GetComicsMediaRequest,
        onResponse: (GetComicsMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )

    fun getSeriesMedia(
        request: GetSeriesMediaRequest,
        onResponse: (GetSeriesMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    )

}