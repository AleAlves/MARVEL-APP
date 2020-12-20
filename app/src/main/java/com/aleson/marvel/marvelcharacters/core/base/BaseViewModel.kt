package com.aleson.marvel.marvelcharacters.core.base

import androidx.lifecycle.ViewModel
import com.aleson.marvel.marvelcharacters.core.model.error.COROUTINES
import com.aleson.marvel.marvelcharacters.core.model.error.Exceptions
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ViewEvent> : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    abstract fun setup()

    abstract fun onError(message: String?)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }

    protected fun async(function: () -> Unit) {
        launch {
            try {
                function.invoke()
            } catch (e: Exception) {
                onError(COROUTINES)
            }
        }
    }


}