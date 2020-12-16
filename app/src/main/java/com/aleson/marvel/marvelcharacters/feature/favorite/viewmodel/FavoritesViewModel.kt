package com.aleson.marvel.marvelcharacters.feature.favorite.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.favorite.usecase.GetFavoritesUseCase
import com.aleson.marvel.marvelcharacters.feature.favorite.view.FavoritesViewEvent
import java.lang.Error

class FavoritesViewModel(
    var getFavoritesUseCase: GetFavoritesUseCase
) : BaseViewModel<FavoritesViewEvent>() {

    override fun setup() {
        getFavorites()
    }

    private fun getFavorites() {
        async {
            getFavoritesUseCase.execute({ response ->
                super.events.value = FavoritesViewEvent.OnLoadFavorites(response?.characters)
            }, { error ->
                onError(error?.message)
            })
        }
    }


    override fun onError(message: String?) {
        super.events.value = FavoritesViewEvent.OnError(message)
    }

}