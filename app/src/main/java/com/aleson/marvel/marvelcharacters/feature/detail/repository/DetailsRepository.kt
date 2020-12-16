package com.aleson.marvel.marvelcharacters.feature.detail.repository

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaResponse

class DetailsRepository(private var source: DetailsDataSource) :
    DetailsDataSource {

    override fun getComicsMedia(
        request: GetComicsMediaRequest,
        onResponse: (GetComicsMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        source.getComicsMedia(request, onResponse, onError)
    }
}