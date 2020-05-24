package com.vginert.expensetracker.data.features.accounts

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository

val mockAccounts = listOf(
    Account(0, "Cash", 100.23F),
    Account(1, "Credit Card", -0.99F),
    Account(2, "Bank account", 10002.56F)
)

class AccountsDataRepository : AccountsRepository {

    // TODO implement real repo, this is mock data for development.
    override suspend fun getUserAccounts(): List<Account> = mockAccounts
}
