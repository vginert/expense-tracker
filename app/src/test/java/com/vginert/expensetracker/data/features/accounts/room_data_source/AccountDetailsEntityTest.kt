package com.vginert.expensetracker.data.features.accounts.room_data_source

import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionDetailEntity
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity
import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.transactions.Transaction
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountDetailsEntityTest {

    companion object {
        private const val AMOUNT = 113222F
    }

    @Test
    fun `On transform to domain should return correct account details`() {
        val expectedAccount: Account = mockk()
        val accountEntity: AccountEntity = mockk {
            every { toDomain() } returns expectedAccount
        }
        val expectedTransaction: Transaction = mockk()
        val transactionEntity: TransactionEntity = mockk {
            every { amount } returns AMOUNT
        }
        val transactionDetailEntity: TransactionDetailEntity = mockk {
            every { transaction } returns transactionEntity
            every { toDomain() } returns expectedTransaction
        }
        val accountDetailsEntity =
            AccountDetailsEntity(accountEntity, listOf(transactionDetailEntity))

        val accountDetailResult = accountDetailsEntity.toDomain()

        assertEquals(expectedAccount, accountDetailResult.account)
        assertTrue(accountDetailResult.transactions.size == 1)
        assertEquals(expectedTransaction, accountDetailResult.transactions[0])
        assertEquals(AMOUNT, accountDetailResult.balance)
    }
}