package com.aleson.marvel.marvelcharacters.feature.character.repository.api

import com.aleson.marvel.marvelcharacters.core.model.ApiSecurityData
import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface GetCharactersRequest {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") publicApiKey: String,
        @Query("hash") hash: String
    ): Call<CharacterDataWrapper>

}