package com.aleson.marvel.marvelcharacters.feature.detail.repository.data

import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ErrorModel
import com.aleson.marvel.marvelcharacters.core.dao.AppDatabase
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.series.SeriesDataWrapper
import com.aleson.marvel.marvelcharacters.core.util.generateHash
import com.aleson.marvel.marvelcharacters.feature.character.di.PUBLIC_KEY
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteResponse
import com.aleson.marvel.marvelcharacters.feature.detail.repository.api.GetMediaApi
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

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

        Connector.request().create(GetMediaApi::class.java)
            .getComicsMedia(
                request.id,
                timeStamp,
                PUBLIC_KEY,
                generateHash(timeStamp)
            ).enqueue(call)
    }

    override fun getSeriesMedia(
        request: GetSeriesMediaRequest,
        onResponse: (GetSeriesMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        val call = object : Callback<SeriesDataWrapper?> {
            override fun onFailure(call: Call<SeriesDataWrapper?>, t: Throwable) {
                onError(ErrorModel(t.toString()))
            }

            override fun onResponse(
                call: Call<SeriesDataWrapper?>,
                response: Response<SeriesDataWrapper?>
            ) {
                if (response.body() == null || response.code() != 200) {
                    onError(
                        ErrorModel(
                            response.message()
                        )
                    )
                } else {
                    onResponse(GetSeriesMediaResponse(response.body() as ComicsDataWrapper))
                }
            }
        }

        Connector.request().create(GetMediaApi::class.java)
            .getSeriesMedia(
                request.id,
                timeStamp,
                PUBLIC_KEY,
                generateHash(timeStamp)
            ).enqueue(call)
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        try {
            val previous = database?.favorites()?.getByName(request.character.name)

            if (previous == null) {
                request.character.favorite = true
                database?.favorites()?.insert(request.character)
                onResponse(UpdateFavoriteResponse(request.character))
            } else {
                request.character.favorite = false
                database?.favorites()?.delete(request.character)
                onResponse(UpdateFavoriteResponse(request.character))
            }

        } catch (e: Exception) {
            onError(ErrorModel(e.toString()))
        }
    }

}