package com.aleson.marvel.marvelcharacters.feature.favorite.di.provider

import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteUseCase
import com.aleson.marvel.marvelcharacters.feature.favorite.repository.FavoritesRepository
import com.aleson.marvel.marvelcharacters.feature.favorite.usecase.GetFavoritesUseCase

class GetFavoritesUseCaseProvider {

    companion object {
        fun provideGetCharactersUseCase(repository: FavoritesRepository) =
            GetFavoritesUseCase(repository)
    }
}