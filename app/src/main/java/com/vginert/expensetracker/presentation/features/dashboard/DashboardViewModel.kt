package com.vginert.expensetracker.presentation.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import com.vginert.expensetracker.domain.features.accounts.AccountDetail
import com.vginert.expensetracker.domain.features.accounts.use_cases.GetAccountsDetailsUseCase
import com.vginert.expensetracker.presentation.core.fragments.BaseViewModel
import com.vginert.expensetracker.presentation.core.lifecycle.Event
import com.vginert.expensetracker.presentation.core.models.AccountDetailModel
import com.vginert.expensetracker.presentation.core.models.toPresentation
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class DashboardViewModel(
    private val getAccountsDetailsUseCase: GetAccountsDetailsUseCase,
    exceptionHandler: ExceptionHandler
) : BaseViewModel(exceptionHandler) {

    private val _goToCreateTransaction: MutableLiveData<Event<Unit>> = MutableLiveData()
    val goToCreateTransaction: LiveData<Event<Unit>> = _goToCreateTransaction

    private val _accountsDetails: MutableLiveData<List<AccountDetail>> =
        MutableLiveData(emptyList())
    val accountsDetails: LiveData<List<AccountDetailModel>> = map(_accountsDetails) {
        it.map(AccountDetail::toPresentation)
    }

    init {
        fetchAccountsDetails()
    }

    fun onCreateTransactionClick() {
        _goToCreateTransaction.value = Event(Unit)
    }

    private fun fetchAccountsDetails() = launch(Main) {
        _accountsDetails.value = withContext(IO) { getAccountsDetailsUseCase() }
    }
}
