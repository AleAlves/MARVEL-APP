package com.aleson.marvel.marvelcharacters.feature.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteRequest
import com.aleson.marvel.marvelcharacters.feature.character.usecase.UpdateFavoriteUseCase
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.*
import com.aleson.marvel.marvelcharacters.feature.detail.view.event.DetailsViewEvent

class DetailsViewModel(
    private val getComicsMedia: GetComicsMediaUseCase,
    private val getSeriesMedia: GetSeriesMediaUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : BaseViewModel<DetailsViewEvent>() {

    var events = MutableLiveData<DetailsViewEvent>()

    fun getComicsMedia(uri: String?, onLoadMedia: (Image?) -> Unit) {
        async {
            getComicsMedia.request = GetComicsMediaRequest(uri.toString())
            getComicsMedia.execute({
                onLoadMedia(it?.comics?.data?.results?.first()?.thumbnail)
            }, {
                print(it?.message)
            })
        }
    }

    fun getSeriesMedia(uri: String?, onLoadMedia: (Image?) -> Unit) {
        async {
            getSeriesMedia.request = GetSeriesMediaRequest(uri)
            getSeriesMedia.execute({
                onLoadMedia(it?.series?.data?.results?.get(0)?.thumbnail)
            }, {
                print(it?.message)
            })
        }
    }

    fun deleteFavorite(character: Character) {
        async {
            updateFavoriteUseCase.request = UpdateFavoriteRequest(character)
            updateFavoriteUseCase.execute({
                events.value = DetailsViewEvent.OnFavoriteDeleted(it?.character?.favorite)
            }, {
                onError(it?.message)
            })
        }
    }

    override fun onError(message: String?) {
        events.value = DetailsViewEvent.OnError(message)
    }

}