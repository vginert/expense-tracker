package com.vginert.expensetracker.presentation.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import com.vginert.expensetracker.presentation.core.fragments.BaseViewModel
import com.vginert.expensetracker.presentation.core.lifecycle.Event

class DashboardViewModel(exceptionHandler: ExceptionHandler) : BaseViewModel(exceptionHandler) {

    private val _goToCreateTransaction: MutableLiveData<Event<Unit>> = MutableLiveData()
    val goToCreateTransaction: LiveData<Event<Unit>> = _goToCreateTransaction

    fun onCreateTransactionClick() {
        _goToCreateTransaction.value = Event(Unit)
    }
}
