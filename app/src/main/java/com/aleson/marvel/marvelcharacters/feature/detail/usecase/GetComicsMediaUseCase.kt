package com.aleson.marvel.marvelcharacters.feature.detail.usecase

import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository

class GetComicsMediaRequest(var id: String) : UseCaseRequest

class GetComicsMediaResponse(val comics: ComicsDataWrapper) : UseCaseResponse

class GetComicsMediaUseCase(val repository: DetailsRepository) : BaseUseCase<GetComicsMediaRequest, GetComicsMediaResponse>() {

    override fun execute(
        onResponse: (GetComicsMediaResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getComicsMedia(GetComicsMediaRequest( request.id), onResponse, onError)
    }

}