package com.aleson.marvel.marvelcharacters.feature.character.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.base.BaseInjector
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.mock
import com.aleson.marvel.marvelcharacters.feature.character.di.factory.CharactersViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersMockDataSourceImpl

class CharactersInjector : BaseInjector() {

    companion object {

        private fun repository(context: Context?): CharactersRepository = CharactersRepository(
            if (mock) CharactersMockDataSourceImpl(
                context,
                database(context)
            ) else CharactersDataSourceImpl(database(context))
        )


        fun provideCharactersViewModelFactory(context: Context?) =
            CharactersViewModelFactory(repository(context))
    }
}