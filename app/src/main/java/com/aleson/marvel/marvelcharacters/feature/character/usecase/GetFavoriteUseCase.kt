package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository


class GetFavoriteRequest(var id: Int) : UseCaseRequest

class GetFavoriteResponse(var isFavorite: Boolean?) : UseCaseResponse

class GetFavoriteUseCase(private val repository: CharactersRepository) :
    BaseUseCase<GetFavoriteRequest, GetFavoriteResponse>() {


    override fun execute(
        onResponse: (GetFavoriteResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getFavoriteStatus(
            super.request,
            { onResponse(it) },
            { onError })
    }
}