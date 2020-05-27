package com.vginert.expensetracker.data.features.transactions

import com.vginert.expensetracker.data.features.transaction.room_data_source.TransactionsDao
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository

class TransactionsDataRepository(
    private val transactionsDao: TransactionsDao
) : TransactionsRepository {

    override suspend fun saveTransaction(
        accountId: Int,
        categoryId: Int,
        amount: Float,
        time: Long
    ) {
        val transaction = TransactionEntity(accountId, categoryId, amount, time = time)
        transactionsDao.insert(transaction)
    }
}
