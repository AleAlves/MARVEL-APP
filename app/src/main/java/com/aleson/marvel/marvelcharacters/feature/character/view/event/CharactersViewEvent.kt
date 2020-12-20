package com.aleson.marvel.marvelcharacters.feature.character.view.event

import com.aleson.marvel.marvelcharacters.core.base.ViewItem
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.character.Resource

sealed class CharactersViewEvent {

    data class OnLoadSearch(val characters: CharacterDataWrapper?) : CharactersViewEvent()

    data class OnLoadMoreCharacters(val characters: MutableList<Character>) : CharactersViewEvent()

    data class OnFavoriteUpdated(val character : Character) : CharactersViewEvent()

    data class OnLoadFavorites(val characters: List<Character>?) : CharactersViewEvent()

    data class OnError(val error: String?) : CharactersViewEvent()

}