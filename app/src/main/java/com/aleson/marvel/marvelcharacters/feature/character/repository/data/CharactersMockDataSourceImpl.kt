package com.aleson.marvel.marvelcharacters.feature.character.repository.data

import android.content.Context
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.core.extension.mockLoader
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