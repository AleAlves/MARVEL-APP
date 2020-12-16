package com.aleson.marvel.marvelcharacters.core.model.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Series(
    @ColumnInfo(name = "items")
    val items: List<Resource>? = null
) : Parcelable