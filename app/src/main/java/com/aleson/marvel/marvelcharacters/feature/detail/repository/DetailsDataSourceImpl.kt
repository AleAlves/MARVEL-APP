package com.aleson.marvel.marvelcharacters.feature.detail.repository

import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.dao.AppDatabase
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.util.generateHash
import com.aleson.marvel.marvelcharacters.feature.character.di.PUBLIC_KEY
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsDataSourceImpl(var database: AppDatabase?) :
    DetailsDataSource {

    private var timeStamp = System.currentTimeMillis().toString()

    override fun getComicsMedia(
        request: GetComicsMediaRequest,
        onResponse: (GetComicsMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {

        val call = object : Callback<ComicsDataWrapper?> {
            override fun onFailure(call: Call<ComicsDataWrapper?>, t: Throwable) {
                onError(ErrorModel(t.toString()))
            }

            override fun onResponse(
                call: Call<ComicsDataWrapper?>,
                response: Response<ComicsDataWrapper?>
            ) {
                if (response.body() == null || response.code() != 200) {
                    onError(
                        ErrorModel(
                            response.message()
                        )
                    )
                } else {
                    onResponse(GetComicsMediaResponse(response.body() as ComicsDataWrapper))
                }
            }
        }

        Connector.request().create(GetComicsMediaApi::class.java)
            .getComicsMedia(
                request.id,
                timeStamp,
                PUBLIC_KEY,
                generateHash(timeStamp)
            ).enqueue(call)
    }

}