package com.aleson.marvel.marvelcharacters.feature.character.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.base.BaseInjector
import com.aleson.marvel.marvelcharacters.feature.character.di.factory.CharactersViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

class CharactersInjector : BaseInjector(){

    companion object {
        private fun repository(context: Context?) = CharactersRepository(CharactersDataSourceImpl(database(context)))
        fun provideCharactersViewModelFactory(context: Context?) = CharactersViewModelFactory(repository(context))
    }
}