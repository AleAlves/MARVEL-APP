package com.aleson.marvel.marvelcharacters.feature.character.di.provider

import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*

class CharactersUseCaseProvider {

    companion object {
        fun provideGetCharactersUseCase(repository: CharactersRepository) = GetCharactersUseCase(repository)
        fun provideUpdateFavoriteUseCase(repository: CharactersRepository) = UpdateFavoriteUseCase(repository)
        fun provideGetFavoriteUseCase(repository: CharactersRepository) = GetFavoriteUseCase(repository)
        fun provideGetFavoritessUseCase(repository: CharactersRepository) = GetFavoritesUseCase(repository)
    }
}