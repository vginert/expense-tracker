package com.vginert.expensetracker.data.features.transactions

import com.vginert.expensetracker.data.features.accounts.mockAccounts
import com.vginert.expensetracker.data.features.categories.mockCategories
import com.vginert.expensetracker.domain.features.transactions.Transaction
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository
import java.util.*

val transactions = mutableListOf<Transaction>()

class TransactionsDataRepository : TransactionsRepository {

    // TODO implement real repo, this is mock data for development.
    override suspend fun saveTransaction(accountId: Int, categoryId: Int, amount: Float) {
        val account = mockAccounts.find { it.id == accountId }
            ?: throw IllegalArgumentException("Account not found")
        val category = mockCategories.find { it.id == categoryId }
            ?: throw IllegalArgumentException("Category not found")

        val transaction = Transaction(0, account, category, amount, Date())
        transactions.add(transaction)
    }
}
