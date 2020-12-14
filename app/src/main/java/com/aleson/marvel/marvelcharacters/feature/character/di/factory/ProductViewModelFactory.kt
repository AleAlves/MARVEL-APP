package com.aleson.marvel.marvelcharacters.feature.character.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleson.marvel.marvelcharacters.feature.character.di.provider.ProductUseCaseProvider
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

class ProductViewModelFactory(private val repository: CharactersRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CharactersViewModel(
        ProductUseCaseProvider.provideGetProductsUseCase(repository)
    ) as T
}