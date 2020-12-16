package com.aleson.marvel.marvelcharacters.feature.detail.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleson.marvel.marvelcharacters.feature.detail.di.provider.DetailsUseCaseProvider
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository
import com.aleson.marvel.marvelcharacters.feature.detail.viewmodel.DetailsViewModel

class DetailsViewModelFactory(private val repository: DetailsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailsViewModel(
        DetailsUseCaseProvider.providGetComicsMediaUseCase(repository),
        DetailsUseCaseProvider.providGetSeriesMediaUseCase(repository),
        DetailsUseCaseProvider.providUpdateFavoriteUseCase(repository)
    ) as T
}