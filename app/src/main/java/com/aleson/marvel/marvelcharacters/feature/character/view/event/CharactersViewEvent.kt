package com.aleson.marvel.marvelcharacters.feature.character.view.event

import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper

sealed class CharactersViewEvent {

    data class OnLoadSearch(val characters: CharacterDataWrapper?) : CharactersViewEvent()

    data class OnLoadMoreCharacters(val characters: CharacterDataWrapper?) : CharactersViewEvent()

    data class OnFavoriteUpdated(val character : Character) : CharactersViewEvent()

    data class OnLoadFavorites(val characters: List<Character>?) : CharactersViewEvent()

    data class OnFavoriteStatusLoaded(val id : Int, val status : Boolean) : CharactersViewEvent()

    data class OnError(val error: String?) : CharactersViewEvent()

}