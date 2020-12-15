package com.aleson.marvel.marvelcharacters.feature.character.di.provider

import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteUseCase

class CharactersUseCaseProvider {

    companion object {
        fun provideGetCharactersUseCase(repository: CharactersRepository) = GetCharactersUseCase(repository)
        fun provideSaveFavoriteUseCase(repository: CharactersRepository) = SaveFavoriteUseCase(repository)
    }
}