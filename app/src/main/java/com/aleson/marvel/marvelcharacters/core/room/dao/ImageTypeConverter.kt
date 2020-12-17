package com.aleson.marvel.marvelcharacters.core.room.dao

import androidx.room.TypeConverter
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.google.gson.Gson

class ImageTypeConverter {

    @TypeConverter
    fun fromString(image: String?): Image? {
        if (image == null) return null
        val gson = Gson()
        return gson.fromJson<Image>(
            image,
            Image::class.java
        )
    }

    @TypeConverter
    fun toString(image: Image?): String? {
        if (image == null) return null
        val gson = Gson()
        return gson.toJson(image)
    }
}