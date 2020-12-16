package com.aleson.marvel.marvelcharacters.core.model.comics

import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataContainer

data class ComicsDataWrapper(
    val copyright: String = "",
    val code: Int = 0,
    val data: ComicsDataContainer,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val status: String = ""
)