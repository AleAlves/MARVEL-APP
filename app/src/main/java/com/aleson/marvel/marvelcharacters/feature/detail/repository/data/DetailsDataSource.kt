package com.aleson.marvel.marvelcharacters.feature.detail.repository.data

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.UpdateDataSource
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.*

interface DetailsDataSource : UpdateDataSource{

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