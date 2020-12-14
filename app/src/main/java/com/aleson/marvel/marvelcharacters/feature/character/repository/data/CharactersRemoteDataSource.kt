package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.model.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.util.toMd5
import com.aleson.marvel.marvelcharacters.feature.character.di.*
import com.aleson.marvel.marvelcharacters.feature.character.repository.api.GetCharactersRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersRemoteDataSource : CharactersDataSource {

    private var timeStamp = System.currentTimeMillis().toString()

    override fun getCharacters(
        onResponse: (CharacterDataWrapper) -> Unit,
        onError: () -> Unit
    ) {
        val call = object : Callback<CharacterDataWrapper?> {
            override fun onFailure(call: Call<CharacterDataWrapper?>, t: Throwable) {
                onError()
            }

            override fun onResponse(
                call: Call<CharacterDataWrapper?>,
                response: Response<CharacterDataWrapper?>
            ) {
                if (response.body() == null || response.code() != 200) {
                    onError()
                } else {
                    onResponse(response.body() as CharacterDataWrapper)
                }
            }
        }

        Connector.request().create(GetCharactersRequest::class.java).getCharacters(timeStamp, PUBLIC_KEY, generateHash()).enqueue(call)
    }

    private fun generateHash(): String {
        return (timeStamp + PRIVATE_KEY + PUBLIC_KEY).toMd5()
    }
}