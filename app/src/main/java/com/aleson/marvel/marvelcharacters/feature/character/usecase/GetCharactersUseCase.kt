package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.Values.Companion.limit
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.Values.Companion.order
import com.aleson.marvel.marvelcharacters.core.base.BaseUseCase
import com.aleson.marvel.marvelcharacters.core.base.UseCaseRequest
import com.aleson.marvel.marvelcharacters.core.base.UseCaseResponse
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository


class GetCharactersRequest(
    var limite: String = limit.toString(),
    var orderBy: String = order,
    var name: String?,
    var offset: Int? = 0
) :
    UseCaseRequest

class GetCharactersResponse(val characters: CharacterDataWrapper) : UseCaseResponse

class GetCharactersUseCase(private val repository: CharactersRepository) :
    BaseUseCase<GetCharactersRequest, GetCharactersResponse>() {

    override fun execute(
        onResponse: (GetCharactersResponse?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        repository.getCharacters(
            super.request,
            { response -> onResponse(response) },
            { onError(it) })
    }
}