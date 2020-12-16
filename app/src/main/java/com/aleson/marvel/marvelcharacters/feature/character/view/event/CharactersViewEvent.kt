package com.aleson.marvel.marvelcharacters.feature.character.view.event

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper

sealed class CharactersViewEvent {

    data class OnLoadCharacters(val characters: CharacterDataWrapper?) : CharactersViewEvent()

    data class OnError(val error: String?) : CharactersViewEvent()

}