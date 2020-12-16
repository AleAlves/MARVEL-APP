package com.aleson.marvel.marvelcharacters.core.model.character

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Image(
    @SerializedName("path")
    val path: String? = "",
    @SerializedName("extension")
    val extension: String? = ""
) : Parcelable