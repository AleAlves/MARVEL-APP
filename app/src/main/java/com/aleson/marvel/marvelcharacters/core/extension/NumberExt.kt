package com.aleson.marvel.marvelcharacters.core.extension

import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.Values.Companion.limit


fun position(size: Int): Int {
    return (size - limit) + 1
}
