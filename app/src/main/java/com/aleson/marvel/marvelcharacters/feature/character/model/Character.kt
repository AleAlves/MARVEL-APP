package com.aleson.marvel.marvelcharacters.feature.character.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: Image? = null,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = ""
) : Parcelable

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE :id")
    fun get(id: Int): Character

    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Character>

    @Insert
    fun insert(users: Character)

    @Delete
    fun delete(user: Character)
}

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