package com.aleson.marvel.marvelcharacters.feature.favorite.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleson.marvel.marvelcharacters.feature.favorite.di.provider.GetFavoritesUseCaseProvider
import com.aleson.marvel.marvelcharacters.feature.favorite.repository.FavoritesRepository
import com.aleson.marvel.marvelcharacters.feature.favorite.viewmodel.FavoritesViewModel

class DetailsViewModelFactory(private val repository: FavoritesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FavoritesViewModel(
            GetFavoritesUseCaseProvider.provideGetCharactersUseCase(
                repository
            )
        ) as T
}