package com.aleson.marvel.marvelcharacters.feature.favorite.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.di.BaseInjector
import com.aleson.marvel.marvelcharacters.feature.favorite.di.factory.FavoritesViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.favorite.repository.FavoritesDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.favorite.repository.FavoritesRepository

class DetailsInjector : BaseInjector() {

    companion object {
        private fun repository(context: Context?) =
            FavoritesRepository(FavoritesDataSourceImpl(database(context)))

        fun provideDetailsInjectorViewModelFactory(context: Context?) =
            FavoritesViewModelFactory(repository(context))
    }
}