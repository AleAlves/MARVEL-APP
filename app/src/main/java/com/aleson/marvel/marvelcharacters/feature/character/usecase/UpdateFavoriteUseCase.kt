package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.UpdateDataSource


class UpdateFavoriteRequest(var character: Character) : UseCaseRequest

class UpdateFavoriteResponse(var character: Character) : UseCaseResponse

class UpdateFavoriteUseCase(private val repository: UpdateDataSource) :
    BaseUseCase<UpdateFavoriteRequest, UpdateFavoriteResponse>() {

    override fun execute(
        onResponse: (UpdateFavoriteResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.updateFavorite(
            super.request,
            { onResponse(it) },
            { onError })
    }
}