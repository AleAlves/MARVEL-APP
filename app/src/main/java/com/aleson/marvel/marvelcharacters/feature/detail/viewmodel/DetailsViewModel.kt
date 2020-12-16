package com.aleson.marvel.marvelcharacters.feature.detail.viewmodel

import com.aleson.marvel.marvelcharacters.core.base.BaseViewModel
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaRequest
import com.aleson.marvel.marvelcharacters.feature.detail.usecase.GetComicsMediaUseCase
import com.aleson.marvel.marvelcharacters.feature.detail.view.DetailsViewEvent

class DetailsViewModel(private val getComicsMedia: GetComicsMediaUseCase) :
    BaseViewModel<DetailsViewEvent>() {

    override fun setup() {
    }

    fun getComicsMedia(id: String, onLoadMedia: (Image?) -> Unit) {
        getComicsMedia.request = GetComicsMediaRequest(id = id)
        getComicsMedia.execute({
            onLoadMedia(it?.comics?.data?.results?.get(0)?.thumbnail)
        }, {
            print("")
        })
    }

    override fun onError(message: String?) {
    }

}