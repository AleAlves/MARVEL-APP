package com.aleson.marvel.marvelcharacters.core.model

data class Character(
    val thumbnail: Image,
    val name: String = "",
    val description: String = ""
)