package com.aleson.marvel.marvelcharacters.feature.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository
import com.aleson.marvel.marvelcharacters.feature.detail.viewmodel.DetailsViewModel

class DetailsViewModelFactory(private val repository: DetailsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailsViewModel(
        DetailsUseCaseProvider.providGetComicsMediaUseCase(repository)
    ) as T
}