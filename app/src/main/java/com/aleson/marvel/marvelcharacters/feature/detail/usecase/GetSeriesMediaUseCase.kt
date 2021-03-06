package com.aleson.marvel.marvelcharacters.feature.detail.usecase

import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.series.SeriesDataWrapper
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository

class GetSeriesMediaRequest(var uri: String?) : UseCaseRequest

class GetSeriesMediaResponse(val series: SeriesDataWrapper) : UseCaseResponse

class GetSeriesMediaUseCase(val repository: DetailsRepository) :
    BaseUseCase<GetSeriesMediaRequest, GetSeriesMediaResponse>() {

    override fun execute(
        onResponse: (GetSeriesMediaResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getSeriesMedia(request, onResponse, onError)
    }

}