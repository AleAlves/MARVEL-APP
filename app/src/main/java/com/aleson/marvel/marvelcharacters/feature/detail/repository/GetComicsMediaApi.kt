package com.aleson.marvel.marvelcharacters.feature.detail.repository

import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetComicsMediaApi {

    @GET("/v1/public/comics/{id}")
    fun getComicsMedia(
        @Path("id") path: String,
        @Query("ts") ts: String,
        @Query("apikey") publicApiKey: String,
        @Query("hash") hash: String
    ): Call<ComicsDataWrapper>

}