package com.vginert.expensetracker.presentation.core.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import com.vginert.expensetracker.presentation.core.lifecycle.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel(
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val _unknownError: MutableLiveData<Event<Throwable>> = MutableLiveData()
    val unknownError: LiveData<Event<Throwable>> = _unknownError

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _unknownError.value = Event(exception)
        exceptionHandler.handleException(exception)
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(context + coroutineExceptionHandler, start, block)
}
