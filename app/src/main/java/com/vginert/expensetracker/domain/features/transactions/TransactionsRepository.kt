package com.vginert.expensetracker.domain.features.transactions

import java.util.*

interface TransactionsRepository {
    suspend fun saveTransaction(
        accountId: Int,
        categoryId: Int,
        amount: Float,
        time: Long = Date().time
    )
}
