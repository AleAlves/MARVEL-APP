package com.aleson.marvel.marvelcharacters.core.room.dao

import androidx.room.TypeConverter
import com.aleson.marvel.marvelcharacters.core.model.character.Comics
import com.google.gson.Gson

class ComicsTypeConverter {

    @TypeConverter
    fun fromString(comics: String?): Comics? {
        if (comics == null) return null
        return Gson().fromJson<Comics>(
            comics,
            Comics::class.java
        )
    }

    @TypeConverter
    fun toString(comics: Comics?): String? {
        if (comics == null) return null
        return Gson().toJson(comics)
    }
}