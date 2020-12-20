package com.aleson.marvel.marvelcharacters.core.helper

import android.content.Context
import android.net.ConnectivityManager


class InternetHelper {

    fun available(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}