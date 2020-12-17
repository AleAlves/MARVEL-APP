package com.aleson.marvel.marvelcharacters.core.room.dao

import androidx.room.TypeConverter
import com.aleson.marvel.marvelcharacters.core.model.character.Series
import com.google.gson.Gson

class SeriesTypeConverter {

    @TypeConverter
    fun fromString(comics: String?): Series? {
        if (comics == null) return null
        val gson = Gson()
        return gson.fromJson<Series>(
            comics,
            Series::class.java
        )
    }

    @TypeConverter
    fun toString(comics: Series?): String? {
        if (comics == null) return null
        val gson = Gson()
        return gson.toJson(comics)
    }
}