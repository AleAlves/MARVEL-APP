package com.aleson.marvel.marvelcharacters.core.model.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Comics(
    @ColumnInfo(name = "items")
    val items: List<ComicsItem>? = null
) : Parcelable