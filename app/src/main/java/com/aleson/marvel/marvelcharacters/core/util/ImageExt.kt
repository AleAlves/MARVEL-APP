package com.aleson.marvel.marvelcharacters.core.util

import android.content.Context
import android.widget.ImageView
import com.aleson.marvel.marvelcharacters.R
import com.aleson.marvel.marvelcharacters.feature.character.model.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


fun loadImageFromUrl(context: Context, image: Image?, imageView: ImageView){
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(5))
    Glide.with(context).applyDefaultRequestOptions(
        requestOptions
        .placeholder(R.drawable.placeholder))
        .load(getImageReady(image)).into(imageView)
}

fun getImageReady(image: Image?) : String {
    return image?.path.plus(".".plus(image?.extension))
}