package com.aleson.marvel.marvelcharacters.core.extension

import com.aleson.marvel.marvelcharacters.core.ui.GeneralSetup.Companion.privateKey
import com.aleson.marvel.marvelcharacters.core.ui.GeneralSetup.Companion.publicKey

fun generateHash(timeStamp: String): String {
    return (timeStamp + privateKey + publicKey).toMd5()
}

fun getTimeStamp() = System.currentTimeMillis().toString()