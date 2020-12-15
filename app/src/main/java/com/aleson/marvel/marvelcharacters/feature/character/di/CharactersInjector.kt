package com.aleson.marvel.marvelcharacters.feature.character.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.di.BaseInjector
import com.aleson.marvel.marvelcharacters.feature.character.di.factory.CharactersViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

const val PUBLIC_KEY = "d3b3c202369a2f3050fc60dc05ec6b9b";
const val PRIVATE_KEY = "3d83579da752927ecd8344fb26e147be18e0aee0";

class CharactersInjector : BaseInjector(){

    companion object {
        private fun repository(context: Context?) = CharactersRepository(CharactersDataSourceImpl(database(context)))
        fun provideCharactersViewModelFactory(context: Context?) = CharactersViewModelFactory(repository(context))
    }
}