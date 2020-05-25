package com.vginert.expensetracker.data.features.accounts

import com.vginert.expensetracker.data.features.transactions.mockTransactions
import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.AccountDetail
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository

val mockAccounts = listOf(
    Account(0, "Cash", 100.23F),
    Account(1, "Credit Card", -0.99F),
    Account(2, "Bank account", 10002.56F)
)

class AccountsDataRepository : AccountsRepository {

    // TODO implement real repo, this is mock data for development.
    override suspend fun getUserAccounts(): List<Account> = mockAccounts

    // TODO implement real repo, this is mock data for development.
    override suspend fun getAccountsDetails(): List<AccountDetail> {
        return mockAccounts.map { account ->
            val transactions = mockTransactions.filter { it.account.id == account.id }.subList(0, 9)
            AccountDetail(
                account,
                transactions,
                transactions.map { it.amount }.sum()
            )
        }
    }
}
