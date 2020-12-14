package com.aleson.marvel.marvelcharacters.feature.character.di.provider

import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase

class ProductUseCaseProvider {

    companion object {
        fun provideGetProductsUseCase(repository: CharactersRepository) = GetCharactersUseCase(repository)
    }
}