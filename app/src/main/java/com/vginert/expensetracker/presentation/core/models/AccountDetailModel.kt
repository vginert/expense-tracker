package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.accounts.AccountDetail

data class AccountDetailModel(
    val account: AccountModel,
    val transactions: List<TransactionModel>,
    val balance: Float
)

fun AccountDetail.toPresentation() = AccountDetailModel(
    account.toPresentation(),
    transactions.map { it.toPresentation() },
    balance
)