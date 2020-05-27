package com.vginert.expensetracker.data.features.accounts

import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountEntity
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountsDao
import com.vginert.expensetracker.domain.features.accounts.Account
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountsDataRepositoryTest {

    @Test
    fun `On get user account should return a list of account from accounts dao`() {
        val expectedAccount: Account = mockk()
        val accountEntity: AccountEntity = mockk {
            every { toDomain() } returns expectedAccount
        }
        val accountsDao: AccountsDao = mockk {
            coEvery { getAll() } returns listOf(accountEntity)
        }
        val accountsDataRepository = AccountsDataRepository(accountsDao)

        val userAccountsResult = runBlocking { accountsDataRepository.getUserAccounts() }

        coVerify { accountsDao.getAll() }
        assertTrue(userAccountsResult.size == 1)
        assertEquals(expectedAccount, userAccountsResult[0])
    }
}