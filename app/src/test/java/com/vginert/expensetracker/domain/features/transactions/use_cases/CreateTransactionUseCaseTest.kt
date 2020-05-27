package com.vginert.expensetracker.domain.features.transactions.use_cases

import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CreateTransactionUseCaseTest {

    @Test
    fun `On invoke should dave transaction on transactions repository`() {
        val amount = 2.3F
        val accountId = 0
        val categoryId = 1
        val transactionsRepository: TransactionsRepository = mockk(relaxed = true)
        val createTransactionUseCase = CreateTransactionUseCase(transactionsRepository)

        runBlocking { createTransactionUseCase(accountId, categoryId, amount) }

        coVerify { transactionsRepository.saveTransaction(accountId, categoryId, amount, any()) }
    }
}
