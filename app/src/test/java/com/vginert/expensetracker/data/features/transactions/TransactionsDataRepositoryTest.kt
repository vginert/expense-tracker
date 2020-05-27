package com.vginert.expensetracker.data.features.transactions

import com.vginert.expensetracker.data.features.transaction.room_data_source.TransactionsDao
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TransactionsDataRepositoryTest {

    companion object {
        private const val ACCOUNT_ID = 1213
        private const val CATEGORY_ID = 4342
        private const val AMOUNT = 2323213F
        private const val TIME = 2323213213213L
    }

    @Test
    fun `On save transaction should insert transaction in transactions dao`() {
        val expectedTransactionEntity =
            TransactionEntity(ACCOUNT_ID, CATEGORY_ID, AMOUNT, time = TIME)
        val transactionsDao: TransactionsDao = mockk(relaxed = true)
        val transactionsDataRepository = TransactionsDataRepository(transactionsDao)

        runBlocking {
            transactionsDataRepository.saveTransaction(
                ACCOUNT_ID,
                CATEGORY_ID,
                AMOUNT,
                TIME
            )
        }

        coVerify { transactionsDao.insert(eq(expectedTransactionEntity)) }
    }
}
