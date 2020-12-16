package com.aleson.marvel.marvelcharacters.core.model.series

data class SeriesDataContainer(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: ArrayList<SeriesMedia>?
)