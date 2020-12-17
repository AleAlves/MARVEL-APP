package com.aleson.marvel.marvelcharacters.core.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleson.marvel.marvelcharacters.core.model.character.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(ImageTypeConverter::class, ComicsTypeConverter::class, SeriesTypeConverter::class)
abstract class RoomLocalDataBase : RoomDatabase() {
    abstract fun favorites(): CharacterDao
}