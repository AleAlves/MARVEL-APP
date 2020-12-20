package com.aleson.marvel.marvelcharacters.core.helper

import com.aleson.marvel.marvelcharacters.core.ApplicationSetup
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import retrofit2.Call
import retrofit2.Callback

class ConnectorHelper<T> {

    fun doCall(
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

        request.enqueue(call)
    }

}