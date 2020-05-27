package com.vginert.expensetracker.domain.features.transactions.use_cases

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.categories.Category
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CreateTransactionUseCaseTest {

    @Test
    fun `On invoke should dave transaction on transactions repository`() {
        val amount = 2.3F
        val accountId = 0
        val account: Account = mockk {
            every { id } returns accountId
        }
        val categoryId = 1
        val categoryType = Category.Type.INCOME
        val category: Category = mockk {
            every { id } returns categoryId
            every { type } returns categoryType
        }
        val transactionsRepository: TransactionsRepository = mockk(relaxed = true)
        val createTransactionUseCase = CreateTransactionUseCase(transactionsRepository)

        runBlocking { createTransactionUseCase(account, category, amount) }

        coVerify { transactionsRepository.saveTransaction(accountId, categoryId, amount, any()) }
    }
}
