package com.aleson.marvel.marvelcharacters.core.extension

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.Values.Companion.limit
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.digest
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toMd5(): String {
    try {
        val md5Encoder = MessageDigest.getInstance(digest)
        val digest = md5Encoder.digest(this.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    } catch (e: NoSuchAlgorithmException) {
    }
    return ""
}

fun offsetSchema(offset: Int, onOffSet: (Int) -> Unit) {
    onOffSet(offset + limit)
}

fun readTextFile(inputStream: InputStream?): String {
    val outputStream = ByteArrayOutputStream()
    val buf = ByteArray(1024)
    var len: Int
    try {
        while (inputStream?.read(buf).also { len = it!! } != -1) {
            outputStream.write(buf, 0, len)
        }
        outputStream.close()
        inputStream?.close()
    } catch (e: IOException) {
    }
    return outputStream.toString()
}

inline fun <reified T> mockLoader(context: Context?, resource: Int): T {
    val mapper: InputStream? = context?.resources?.openRawResource(resource)
    val jsonString: String = readTextFile(mapper)
    val gson = Gson()

    return gson.fromJson(jsonString, T::class.java)
}