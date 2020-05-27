package com.vginert.expensetracker.data.features.transactions.room_data_source

import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.domain.features.categories.Category
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TransactionDetailEntityTest {

    companion object {
        private const val TRANSACTION_ID = 221332
        private const val TRANSACTION_AMOUNT = 1234532F
        private val TRANSACTION_TIME = Date().time
    }

    @Test
    fun `On transform to domain should return correct transaction detail`() {
        val transactionEntity: TransactionEntity = mockk {
            every { id } returns TRANSACTION_ID
            every { amount } returns TRANSACTION_AMOUNT
            every { time } returns TRANSACTION_TIME
        }
        val expectedCategory: Category = mockk()
        val categoryEntity: CategoryEntity = mockk() {
            every { toDomain() } returns expectedCategory
        }
        val transactionDetailEntity = TransactionDetailEntity(transactionEntity, categoryEntity)

        val transactionDetailResult = transactionDetailEntity.toDomain()

        assertEquals(TRANSACTION_ID, transactionDetailResult.id)
        assertEquals(TRANSACTION_AMOUNT, transactionDetailResult.amount)
        assertEquals(TRANSACTION_TIME, transactionDetailResult.time.time)
        verify { categoryEntity.toDomain() }
        assertEquals(expectedCategory, transactionDetailResult.category)
    }

}