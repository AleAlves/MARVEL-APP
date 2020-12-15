package com.aleson.marvel.marvelcharacters.feature.character.model

data class CharacterDataContainer(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: ArrayList<Character>?
)