package com.aleson.marvel.marvelcharacters.feature.detail.di

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.di.BaseInjector
import com.aleson.marvel.marvelcharacters.feature.detail.di.factory.DetailsViewModelFactory
import com.aleson.marvel.marvelcharacters.feature.detail.repository.data.DetailsDataSourceImpl
import com.aleson.marvel.marvelcharacters.feature.detail.repository.DetailsRepository

class DetailsInjector : BaseInjector(){

    companion object {
        private fun repository(context: Context?) = DetailsRepository(
            DetailsDataSourceImpl(
                database(context)
            )
        )
        fun provideDetailsViewModelFactory(context: Context?) =
            DetailsViewModelFactory(
                repository(context)
            )
    }
}