package com.aleson.marvel.marvelcharacters.feature.detail.view.event

import com.aleson.marvel.marvelcharacters.core.model.comics.ComicsDataWrapper

sealed class DetailsViewEvent {

    data class OnLoadComicsMedia(val comics: ComicsDataWrapper?) : DetailsViewEvent()

    data class OnError(val error: String?) : DetailsViewEvent()

}