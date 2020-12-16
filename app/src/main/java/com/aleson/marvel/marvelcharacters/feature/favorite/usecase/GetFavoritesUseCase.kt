package com.aleson.marvel.marvelcharacters.feature.favorite.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.favorite.repository.FavoritesRepository

class GetavoritesResponse(val characters: List<Character>) : UseCaseResponse

class GetFavoritesUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<UseCaseRequest, GetavoritesResponse>() {

    override fun execute(
        onResponse: (GetavoritesResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getFavorites(onResponse, onError)
    }
}