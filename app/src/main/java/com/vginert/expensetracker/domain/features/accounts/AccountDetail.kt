package com.vginert.expensetracker.domain.features.accounts

import com.vginert.expensetracker.domain.features.transactions.Transaction

data class AccountDetail(
    val account: Account,
    val transactions: List<Transaction>,
    val balance: Float
)
