package com.aleson.marvel.marvelcharacters.feature.character.view.event

import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper

sealed class CharactersViewEvent {

    data class OnLoadCharacters(val characters: CharacterDataWrapper?) : CharactersViewEvent()

    data class OnFavoriteSaved(val character : Character) : CharactersViewEvent()

    data class OnFavoriteStatusLoaded(val id : Int, val status : Boolean) : CharactersViewEvent()

    data class OnError(val error: String?) : CharactersViewEvent()

}