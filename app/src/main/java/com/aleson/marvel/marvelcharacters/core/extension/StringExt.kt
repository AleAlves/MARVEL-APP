package com.aleson.marvel.marvelcharacters.core.extension

import com.aleson.marvel.marvelcharacters.core.ui.GeneralSetup
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toMd5(): String {
    try {
        val md5Encoder = MessageDigest.getInstance("MD5")
        val digest = md5Encoder.digest(this.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    } catch (e: NoSuchAlgorithmException) {
    }
    return ""
}

fun generateOffset(offset: Int, onOffSet: (Int) -> Unit) {
    onOffSet(offset + GeneralSetup.limit)
}