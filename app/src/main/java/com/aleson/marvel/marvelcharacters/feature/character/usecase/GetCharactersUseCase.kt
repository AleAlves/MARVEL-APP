package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository


class GetCharactersRequest : UseCaseRequest

class GetCharactersResponse(val characters: CharacterDataWrapper) : UseCaseResponse

class GetCharactersUseCase(private val repository: CharactersRepository) :
    BaseUseCase<GetCharactersRequest, GetCharactersResponse>() {

    override fun execute(onResponse: (GetCharactersResponse?) -> Unit) {
        repository.getCharacters({ response -> onResponse(GetCharactersResponse(response)) }, {})
    }
}