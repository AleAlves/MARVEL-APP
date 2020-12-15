package com.aleson.marvel.marvelcharacters.feature.favorite.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleson.marvel.marvelcharacters.feature.character.model.Character
import com.aleson.marvel.marvelcharacters.feature.character.model.CharacterDao
import com.aleson.marvel.marvelcharacters.feature.character.model.ImageTypeConverter

@Database(entities = arrayOf(Character::class), version = 1)
@TypeConverters(ImageTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favorites(): CharacterDao
}