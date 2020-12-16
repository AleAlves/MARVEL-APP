package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository


class GetCharactersRequest(var limite: String, var orderBy: String) : UseCaseRequest

class GetCharactersResponse(val characters: CharacterDataWrapper) : UseCaseResponse

class GetCharactersUseCase(private val repository: CharactersRepository) :
    BaseUseCase<GetCharactersRequest, GetCharactersResponse>() {

    override fun execute(
        onResponse: (GetCharactersResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getCharacters(
            super.request,
            { response -> onResponse(GetCharactersResponse(response)) },
            { onError })
    }
}