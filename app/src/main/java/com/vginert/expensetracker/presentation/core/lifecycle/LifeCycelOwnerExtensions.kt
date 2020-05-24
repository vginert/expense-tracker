package com.vginert.expensetracker.presentation.core.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, EventObserver(body))
