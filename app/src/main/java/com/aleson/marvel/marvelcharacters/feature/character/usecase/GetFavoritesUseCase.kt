package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

class GetFavoritesResponse(val characters: List<Character>) : UseCaseResponse

class GetFavoritesUseCase(private val repository: CharactersRepository) :
    BaseUseCase<UseCaseRequest, GetFavoritesResponse>() {

    override fun execute(
        onResponse: (GetFavoritesResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getFavorites(
            { response -> onResponse(GetFavoritesResponse(response.characters)) },
            { onError })
    }
}