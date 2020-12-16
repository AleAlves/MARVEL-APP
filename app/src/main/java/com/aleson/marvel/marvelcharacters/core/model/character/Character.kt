package com.aleson.marvel.marvelcharacters.core.model.character

import android.os.Parcelable
import androidx.room.*
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
    val description: String = "",
    @ColumnInfo(name = "comics")
    val comics: Comics? = null
) : Parcelable