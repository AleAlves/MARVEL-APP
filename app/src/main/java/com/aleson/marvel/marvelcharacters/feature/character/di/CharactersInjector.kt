package com.aleson.marvel.marvelcharacters.feature.character.di

import com.aleson.marvel.marvelcharacters.feature.character.di.factory.ProductViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersRemoteDataSource
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository

const val PUBLIC_KEY = "d3b3c202369a2f3050fc60dc05ec6b9b";
const val PRIVATE_KEY = "3d83579da752927ecd8344fb26e147be18e0aee0";
internal const val TIME_STAMP_KEY = "ts"
internal const val HASH_KEY = "hash"
internal const val API_KEY = "apikey"
internal const val LIMIT_KEY = "limit"

class CharactersInjector {

    companion object {
        private fun repository() = CharactersRepository(CharactersRemoteDataSource())
        fun provideCharactersViewModelFactory() = ProductViewModelFactory(repository())
    }
}