package com.aleson.marvel.marvelcharacters.core.helper

import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetFavoriteResponse
import com.aleson.marvel.marvelcharacters.feature.character.usecase.GetFavoritesResponse
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteResponse

class RoomHelper(private var database: RoomLocalDataBase?) {


    fun update(
        character: Character,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        try {
            val previous = database?.favorites()?.getByName(character.name)

            if (previous == null) {
                character.favorite = true
                database?.favorites()?.insert(character)
                onResponse(UpdateFavoriteResponse(character))
            } else {
                character.favorite = false
                database?.favorites()?.delete(character)
                onResponse(UpdateFavoriteResponse(character))
            }

        } catch (e: Exception) {
            onError(ErrorModel(e.toString()))
        }
    }

    fun getStatus(
        id: Int,
        onResponse: (GetFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        try {
            val previous = database?.favorites()?.isFavorite(id)
            onResponse(GetFavoriteResponse(previous))
        } catch (e: Exception) {
            onError(ErrorModel(e.toString()))
        }
    }

    fun fetch(onResponse: (GetFavoritesResponse) -> Unit, onError: (ErrorModel) -> Unit) {
        try {
            val characters = database?.favorites()?.getAll()
            characters?.let { GetFavoritesResponse(it) }?.let { onResponse(it) }
        } catch (e: java.lang.Exception) {
            onError(ErrorModel(e.toString()))
        }
    }

}