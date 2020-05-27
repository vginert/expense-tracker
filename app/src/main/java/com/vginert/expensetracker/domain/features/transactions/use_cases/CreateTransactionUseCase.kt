package com.vginert.expensetracker.domain.features.transactions.use_cases

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.categories.Category
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository

class CreateTransactionUseCase(
    private val transactionsRepository: TransactionsRepository
) {

    suspend operator fun invoke(account: Account, category: Category, amount: Float) {
        val formalizedAmount = formalizeAmount(amount, category.type)
        transactionsRepository.saveTransaction(account.id, category.id, formalizedAmount)
    }

    private fun formalizeAmount(amount: Float, categoryType: Category.Type): Float = when {
        categoryType == Category.Type.EXPENSE && amount >= 0 -> amount * -1
        categoryType == Category.Type.INCOME && amount <= 0 -> amount * -1
        else -> amount
    }
}
