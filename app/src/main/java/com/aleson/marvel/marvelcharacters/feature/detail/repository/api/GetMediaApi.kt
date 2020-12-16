package com.aleson.marvel.marvelcharacters.feature.detail.repository.api

import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.series.SeriesDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetMediaApi {

    @GET("/v1/public/comics/{id}")
    fun getComicsMedia(
        @Path("id") path: String,
        @Query("ts") ts: String,
        @Query("apikey") publicApiKey: String,
        @Query("hash") hash: String
    ): Call<ComicsDataWrapper>

    @GET("/v1/public/series/{id}")
    fun getSeriesMedia(
        @Path("id") path: String,
        @Query("ts") ts: String,
        @Query("apikey") publicApiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String = "100"
    ): Call<SeriesDataWrapper>

}