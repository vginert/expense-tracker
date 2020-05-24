package com.vginert.expensetracker.domain.features.transactions.use_cases

import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository

class CreateTransactionUseCase(
    private val transactionsRepository: TransactionsRepository
) {

    suspend operator fun invoke(accountId: Int, categoryId: Int, amount: Float) {
        transactionsRepository.saveTransaction(accountId, categoryId, amount)
    }
}
