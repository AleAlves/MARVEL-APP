package com.aleson.marvel.marvelcharacters.feature.character.repository.api

import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetCharactersApi {

    @GET
    fun getCharacters(@Url url: String): Call<CharacterDataWrapper>
}