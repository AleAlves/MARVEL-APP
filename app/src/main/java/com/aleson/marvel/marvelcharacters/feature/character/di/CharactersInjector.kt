package com.aleson.marvel.marvelcharacters.feature.character.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.base.BaseInjector
import com.aleson.marvel.marvelcharacters.feature.character.di.factory.CharactersViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

const val PUBLIC_KEY = "002326cdaa1631d23bfb6ada5a2d7515";
const val PRIVATE_KEY = "12fdc64385ad40054f84c773588ee2e7c9f0d6af";

class CharactersInjector : BaseInjector(){

    companion object {
        private fun repository(context: Context?) = CharactersRepository(CharactersDataSourceImpl(database(context)))
        fun provideCharactersViewModelFactory(context: Context?) = CharactersViewModelFactory(repository(context))
    }
}