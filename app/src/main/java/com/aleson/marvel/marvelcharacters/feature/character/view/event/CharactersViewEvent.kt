package com.aleson.marvel.marvelcharacters.feature.character.view.event

import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper

sealed class CharactersViewEvent {

    data class OnLoadCharacters(val characters: CharacterDataWrapper?) : CharactersViewEvent()

}