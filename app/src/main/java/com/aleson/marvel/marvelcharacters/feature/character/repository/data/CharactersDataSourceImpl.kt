package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import android.net.Uri
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.API.Companion.characters
import com.aleson.marvel.marvelcharacters.core.extension.generateHash
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.API_KEY
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.HASH
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.HTTP.Companion.sucess
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.LIMIT
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.NAME_START_WITH
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.OFFSET
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.ORDER_BY
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.TIME_STAMP
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.publicKey
import com.aleson.marvel.marvelcharacters.core.extension.getTimeStamp
import com.aleson.marvel.marvelcharacters.feature.character.repository.api.GetCharactersApi
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharactersDataSourceImpl(var database: RoomLocalDataBase?) : CharactersDataSource {

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (GetCharactersResponse) -> Unit,
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
                if (response.body() == null || response.code() != sucess) {
                    onError(ErrorModel(response.message()))
                } else {
                    onResponse(GetCharactersResponse(response.body() as CharacterDataWrapper))
                }
            }
        }

        val timeStamp = getTimeStamp()
        val url = Uri.parse(characters).buildUpon()
            .appendQueryParameter(HASH, generateHash(timeStamp))
            .appendQueryParameter(OFFSET, request.offset.toString())
            .appendQueryParameter(TIME_STAMP, timeStamp)
            .appendQueryParameter(ORDER_BY, request.orderBy)
            .appendQueryParameter(LIMIT, request.limite)
            .appendQueryParameter(API_KEY, publicKey)

        if (!request.name.isNullOrEmpty()) {
            url.appendQueryParameter(NAME_START_WITH, request.name)
        }

        Connector.request().create(GetCharactersApi::class.java)
            .getCharacters(url.build().toString()).enqueue(call)
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

    override fun getFavorites(
        onResponse: (GetFavoritesResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        val characters = database?.favorites()?.getAll()
        characters?.let { GetFavoritesResponse(it) }?.let { onResponse(it) }
    }

}