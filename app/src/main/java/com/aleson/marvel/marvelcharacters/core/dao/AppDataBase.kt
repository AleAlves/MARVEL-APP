package com.aleson.marvel.marvelcharacters.core.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleson.marvel.marvelcharacters.core.model.character.Character

@Database(entities = arrayOf(Character::class), version = 1)
@TypeConverters(ImageTypeConverter::class, ComicsTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favorites(): CharacterDao
}