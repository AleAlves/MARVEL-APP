package com.aleson.marvel.marvelcharacters.feature.favorite.view

import com.aleson.marvel.marvelcharacters.core.model.character.Character

sealed class FavoritesViewEvent {

    data class OnLoadFavorites(val characters: List<Character>?) : FavoritesViewEvent()

    data class OnError(val error: String?) : FavoritesViewEvent()

}