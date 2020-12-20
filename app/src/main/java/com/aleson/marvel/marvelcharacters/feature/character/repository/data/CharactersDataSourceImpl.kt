package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import android.content.Context
import android.net.Uri
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.API.Companion.characters
import com.aleson.marvel.marvelcharacters.core.extension.generateHash
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.API_KEY
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.HASH
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.LIMIT
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.NAME_START_WITH
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.OFFSET
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.ORDER_BY
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.TIME_STAMP
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.publicKey
import com.aleson.marvel.marvelcharacters.core.helper.ConnectorHelper
import com.aleson.marvel.marvelcharacters.core.extension.getTimeStamp
import com.aleson.marvel.marvelcharacters.core.helper.RoomHelper
import com.aleson.marvel.marvelcharacters.feature.character.repository.api.GetCharactersApi
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*


class CharactersDataSourceImpl(
    val context: Context?,
    val database: RoomLocalDataBase?
) : CharactersDataSource {

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (GetCharactersResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {

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

        val caller = Connector.request().create(GetCharactersApi::class.java)
            .getCharacters(url.build().toString())

        ConnectorHelper<CharacterDataWrapper>().doCall(context, caller, onError)
        {
            onResponse(GetCharactersResponse(it))
        }
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        RoomHelper(database).update(request.character, onResponse, onError)
    }

    override fun getFavoriteStatus(
        request: GetFavoriteRequest,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
       RoomHelper(database).getStatus(request.id, onResponse, onError)
    }

    override fun getFavorites(
        onResponse: (GetFavoritesResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        RoomHelper(database).fetch(onResponse, onError)
    }

}