package com.aleson.marvel.marvelcharacters.core.model.character

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataContainer

data class CharacterDataWrapper(
    val copyright: String = "",
    val code: Int = 0,
    val data: CharacterDataContainer,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val status: String = ""
)