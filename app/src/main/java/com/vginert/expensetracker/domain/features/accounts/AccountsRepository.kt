package com.vginert.expensetracker.domain.features.accounts

interface AccountsRepository {
    suspend fun getUserAccounts(): List<Account>
    suspend fun getAccountsDetails(): List<AccountDetail>
}
