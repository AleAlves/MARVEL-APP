package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import android.content.Context
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.extension.mockLoader
import com.aleson.marvel.marvelcharacters.core.helper.RoomHelper
import com.aleson.marvel.marvelcharacters.core.model.character.CharacterDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*

class CharactersMockDataSourceImpl(var context: Context?, var database: RoomLocalDataBase?) :
    CharactersDataSource {

    override fun getCharacters(
        request: GetCharactersRequest,
        onResponse: (GetCharactersResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        onResponse(GetCharactersResponse(mockLoader(context, R.raw.character)))
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