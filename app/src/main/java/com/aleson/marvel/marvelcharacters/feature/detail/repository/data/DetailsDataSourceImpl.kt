package com.aleson.marvel.marvelcharacters.feature.detail.repository.data

import android.content.Context
import android.net.Uri
import br.com.connector.aleson.android.connector.Connector
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper
import com.aleson.marvel.marvelcharacters.core.model.series.SeriesDataWrapper
import com.aleson.marvel.marvelcharacters.core.extension.generateHash
import com.aleson.marvel.marvelcharacters.core.extension.getTimeStamp
import com.aleson.marvel.marvelcharacters.core.room.dao.RoomLocalDataBase
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.publicKey
import com.aleson.marvel.marvelcharacters.core.helper.ConnectorHelper
import com.aleson.marvel.marvelcharacters.core.helper.RoomHelper
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteResponse
import com.aleson.marvel.marvelcharacters.feature.detail.repository.api.GetMediaApi
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.*

class DetailsDataSourceImpl(val context: Context?, val database: RoomLocalDataBase?) :
    DetailsDataSource {

    override fun getComicsMedia(
        request: GetComicsMediaRequest,
        onResponse: (GetComicsMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {

        val timeStamp = getTimeStamp()
        val url = Uri.parse(request.uri).buildUpon()
            .appendQueryParameter(ApplicationSetup.HASH, generateHash(timeStamp))
            .appendQueryParameter(ApplicationSetup.TIME_STAMP, timeStamp)
            .appendQueryParameter(ApplicationSetup.API_KEY, publicKey).toString()

        val caller = Connector.request().create(GetMediaApi::class.java).getComicsMedia(url)

        ConnectorHelper<ComicsDataWrapper>().doCall(context, caller, onError) {
            onResponse(GetComicsMediaResponse(it))
        }
    }

    override fun getSeriesMedia(
        request: GetSeriesMediaRequest,
        onResponse: (GetSeriesMediaResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        val timeStamp = getTimeStamp()
        val url = Uri.parse(request.uri).buildUpon()
            .appendQueryParameter(ApplicationSetup.HASH, generateHash(timeStamp))
            .appendQueryParameter(ApplicationSetup.TIME_STAMP, timeStamp)
            .appendQueryParameter(ApplicationSetup.API_KEY, publicKey).toString()

        val caller = Connector.request().create(GetMediaApi::class.java).getSeriesMedia(url)

        ConnectorHelper<SeriesDataWrapper>().doCall(context, caller, onError)
        {
            GetSeriesMediaResponse(it)
        }
    }

    override fun updateFavorite(
        request: UpdateFavoriteRequest,
        onResponse: (UpdateFavoriteResponse) -> Unit,
        onError: (ErrorModel) -> Unit
    ) {
        RoomHelper(database).update(request.character, onResponse, onError)
    }

}