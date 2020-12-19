package com.aleson.marvel.marvelcharacters.core.model.character

data class CharacterDataWrapper(
    val copyright: String = "",
    val code: Int = 0,
    val data: CharacterDataContainer,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val status: String = ""
)