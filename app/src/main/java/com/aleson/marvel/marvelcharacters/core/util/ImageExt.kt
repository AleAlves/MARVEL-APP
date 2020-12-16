package com.aleson.marvel.marvelcharacters.core.util

import android.content.Context
import android.widget.ImageView
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


fun loadImageFromUrl(context: Context, image: Image?, imageView: ImageView, placeHolder: Int){
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(5))
    Glide.with(context).applyDefaultRequestOptions(
        requestOptions
        .placeholder(placeHolder))
        .load(getImageReady(image)).into(imageView)
}

fun getImageReady(image: Image?) : String {
    return image?.path.plus(".".plus(image?.extension))
}

fun getIdfromURI(path: String?) : String {
    return path?.replace(Regex("[^0-9]"), "")as String
}
