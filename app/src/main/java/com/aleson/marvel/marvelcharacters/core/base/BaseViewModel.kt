package com.aleson.marvel.marvelcharacters.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleson.marvel.marvelcharacters.core.Exceptions
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ViewEvent> : ViewModel(), CoroutineScope {

    var events = MutableLiveData<ViewEvent>()

    private val viewModelJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelJob

    abstract fun setup()

    abstract fun onError(message: String?)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }

    protected fun async(function: () -> Unit) {
        CoroutineScope(coroutineContext).launch {
            try {
                suspend { function.invoke() }
            } catch (e: Exception) {
                onError(Exceptions.COROUTINES.name)
            }
        }
    }


}