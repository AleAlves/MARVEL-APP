package com.aleson.marvel.marvelcharacters.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aleson.marvel.marvelcharacters.core.model.character.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE :id")
    fun get(id: Int): Character

    @Query("SELECT * FROM character WHERE name = :name")
    fun getByName(name: String): Character

    @Query("SELECT favorite FROM character WHERE id = :id")
    fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Character>

    @Insert
    fun insert(users: Character)

    @Delete
    fun delete(user: Character)
}