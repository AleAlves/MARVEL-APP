package com.aleson.marvel.marvelcharacters.feature.detail.repository.api

import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.series.SeriesDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface GetMediaApi {

    @GET
    fun getComicsMedia(
        @Url url: String
    ): Call<ComicsDataWrapper>

    @GET
    fun getSeriesMedia(
        @Url url: String
    ): Call<SeriesDataWrapper>

}