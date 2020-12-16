package com.aleson.marvel.marvelcharacters.feature.detail.view.event

sealed class DetailsViewEvent {

    data class OnFavoriteDeleted(val operation: Boolean?) : DetailsViewEvent()

    data class OnError(val error: String?) : DetailsViewEvent()

}