package com.vginert.expensetracker.domain.features.transactions

interface TransactionsRepository {
    suspend fun saveTransaction(accountId: Int, categoryId: Int, amount: Float)
}
