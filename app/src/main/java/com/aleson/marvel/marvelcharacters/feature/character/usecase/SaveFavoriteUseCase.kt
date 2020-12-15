package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.feature.character.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.model.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository


class SaveFavoriteRequest(var character: Character) : UseCaseRequest

class SaveFavoriteResponse() : UseCaseResponse

class SaveFavoriteUseCase(private val repository: CharactersRepository) :
    BaseUseCase<SaveFavoriteRequest, SaveFavoriteResponse>() {


    override fun execute(
        onResponse: (SaveFavoriteResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.saveFavorite(
            super.request,
            { onResponse(SaveFavoriteResponse()) },
            { onError })
    }
}