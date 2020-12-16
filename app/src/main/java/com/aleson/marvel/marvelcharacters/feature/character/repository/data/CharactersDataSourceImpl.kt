package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.util.generateHash
import com.aleson.marvel.marvelcharacters.feature.character.di.*
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.feature.character.repository.api.GetCharactersApi
import com.aleson.marvel.marvelcharacters.core.dao.AppDatabase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CharactersDataSourceImpl(var database: AppDatabase?) : CharactersDataSource {

    private var timeStamp = System.currentTimeMillis().toString()

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (CharacterDataWrapper) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        val call = object : Callback<CharacterDataWrapper?> {
            override fun onFailure(call: Call<CharacterDataWrapper?>, t: Throwable) {
                onError(ErrorModel(t.toString()))
            }

            override fun onResponse(
                call: Call<CharacterDataWrapper?>,
                response: Response<CharacterDataWrapper?>
            ) {
                if (response.body() == null || response.code() != 200) {
                    onError(
                        ErrorModel(
                            response.message()
                        )
                    )
                } else {
                    onResponse(response.body() as CharacterDataWrapper)
                }
            }
        }

        Connector.request().create(GetCharactersApi::class.java)
            .getCharacters(
                timeStamp,
                PUBLIC_KEY,
                generateHash(timeStamp),
                request.limite,
                request.orderBy
            ).enqueue(call)
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        try {
            val previous = database?.favorites()?.getByName(request.character.name)

            if (previous == null) {
                request.character.favorite = true
                database?.favorites()?.insert(request.character)
                onResponse(UpdateFavoriteResponse(request.character))
            } else {
                request.character.favorite = false
                database?.favorites()?.delete(request.character)
                onResponse(UpdateFavoriteResponse(request.character))
            }

        } catch (e: Exception) {
            onError(ErrorModel(e.toString()))
        }
    }

    override fun getFavoriteStatus(
        request: GetFavoriteRequest,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        try {
            val previous = database?.favorites()?.isFavorite(request.id)
            onResponse(GetFavoriteResponse(previous))
        } catch (e: Exception) {
            onError(ErrorModel(e.toString()))
        }
    }
}