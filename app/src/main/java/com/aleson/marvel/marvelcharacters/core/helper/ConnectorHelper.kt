package com.aleson.marvel.marvelcharacters.core.helper

import android.content.Context
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup
import com.aleson.marvel.marvelcharacters.core.model.error.*
import retrofit2.Call
import retrofit2.Callback

class ConnectorHelper<T> {

    fun doCall(
        context: Context?,
        request: Call<T>,
        onError: (ErrorModel) -> Unit,
        onResponse: (T) -> Unit
    ) {

        val call = object : Callback<T?> {

            override fun onFailure(call: Call<T?>, t: Throwable) {
                onError(ErrorModel(t.toString()))
            }

            override fun onResponse(call: Call<T?>, response: retrofit2.Response<T?>) {
                if (response.body() == null || response.code() != ApplicationSetup.Companion.HTTP.success) {
                    onError(ErrorModel(response.message()))
                } else {
                    onResponse(response.body() as T)
                }
            }
        }

        if (InternetHelper().available(context)) {
            request.enqueue(call)
        } else {
            onError(ErrorModel(NETWORK_MESSAGE, NETWORK_CODE))
        }
    }

}