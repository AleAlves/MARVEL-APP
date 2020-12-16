package com.aleson.marvel.marvelcharacters.core.model.series

data class SeriesDataWrapper(
    val copyright: String = "",
    val code: Int = 0,
    val data: SeriesDataContainer,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val status: String = ""
)