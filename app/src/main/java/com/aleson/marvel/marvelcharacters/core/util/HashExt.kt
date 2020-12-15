package com.aleson.marvel.marvelcharacters.core.util

import com.aleson.marvel.marvelcharacters.feature.character.di.PRIVATE_KEY
import com.aleson.marvel.marvelcharacters.feature.character.di.PUBLIC_KEY

fun generateHash(timeStamp: String): String {
    return (timeStamp + PRIVATE_KEY + PUBLIC_KEY).toMd5()
}