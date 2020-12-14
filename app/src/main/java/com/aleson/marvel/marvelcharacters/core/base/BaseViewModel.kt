package com.aleson.marvel.marvelcharacters.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ViewEvent> : ViewModel(), CoroutineScope {

    var events = MutableLiveData<ViewEvent>()

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    abstract fun setup()

    abstract fun onError()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }

    protected fun async(function: () -> Unit) {
        launch {
            try {
                function.invoke()
            } catch (e: Exception) {
                onError()
            }
        }
    }


}