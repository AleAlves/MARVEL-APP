package com.aleson.marvel.marvelcharacters.feature.detail.di.provider

import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetCharactersUseCase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.SaveFavoriteUseCase
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaUseCase
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetSeriesMediaUseCase

class DetailsUseCaseProvider {

    companion object {
        fun providGetComicsMediaUseCase(repository: DetailsRepository) = GetComicsMediaUseCase(repository)
        fun providGetSeriesMediaUseCase(repository: DetailsRepository) = GetSeriesMediaUseCase(repository)
    }
}