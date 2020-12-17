package com.aleson.marvel.marvelcharacters.feature.detail.viewmodel

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

    override fun setup() {
    }

    fun getComicsMedia(id: String, onLoadMedia: (Image?) -> Unit) {
        async {
            getComicsMedia.request = GetComicsMediaRequest(id)
            getComicsMedia.execute({
                onLoadMedia(it?.comics?.data?.results?.get(0)?.thumbnail)
            }, {
                print("")
            })
        }
    }

    fun getSeriesMedia(id: String, onLoadMedia: (Image?) -> Unit) {
        async {
            getSeriesMedia.request = GetSeriesMediaRequest(id)
            getComicsMedia.execute({
                onLoadMedia(it?.comics?.data?.results?.get(0)?.thumbnail)
            }, {
                print("")
            })
        }
    }

    fun deleteFavorite(character: Character) {
        async {
            updateFavoriteUseCase.request = UpdateFavoriteRequest(character)
            updateFavoriteUseCase.execute({
                super.events.value = DetailsViewEvent.OnFavoriteDeleted(it?.character?.favorite)
            }, {
                onError(it?.message)
            })
        }
    }

    override fun onError(message: String?) {
    }

}