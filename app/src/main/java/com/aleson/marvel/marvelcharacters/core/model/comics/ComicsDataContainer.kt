package com.aleson.marvel.marvelcharacters.core.model.comics

data class ComicsDataContainer(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: ArrayList<ComicsMedia>?
)