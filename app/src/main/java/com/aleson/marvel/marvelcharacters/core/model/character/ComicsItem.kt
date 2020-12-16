package com.aleson.marvel.marvelcharacters.core.model.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ComicsItem(
    @ColumnInfo(name = "resourceURI")
    val resourceURI: String? = null,
    @ColumnInfo(name = "name")
    val name: String = ""
) : Parcelable