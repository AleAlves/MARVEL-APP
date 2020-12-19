package com.aleson.marvel.marvelcharacters.core.model.comics


data class ComicsDataWrapper(
    val copyright: String = "",
    val code: Int = 0,
    val data: ComicsDataContainer,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val status: String = ""
)