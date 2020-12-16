package com.aleson.marvel.marvelcharacters.core.model.character

import com.aleson.marvel.marvelcharacters.core.model.character.Character

data class CharacterDataContainer(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: ArrayList<Character>?
)