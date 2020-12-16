package com.aleson.marvel.marvelcharacters.feature.character.repository.api

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GetCharactersApi {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") publicApiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String,
        @Query("orderBy") orderBy: String
    ): Call<CharacterDataWrapper>

}