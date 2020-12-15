package com.aleson.marvel.marvelcharacters.feature.character.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleson.marvel.marvelcharacters.feature.character.di.provider.CharactersUseCaseProvider
import com.aleson.marvel.marvelcharacters.feature.character.viewmodel.CharactersViewModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

class CharactersViewModelFactory(private val repository: CharactersRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CharactersViewModel(
        CharactersUseCaseProvider.provideGetCharactersUseCase(repository),
        CharactersUseCaseProvider.provideSaveFavoriteUseCase(repository)
    ) as T
}