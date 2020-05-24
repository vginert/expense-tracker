package com.vginert.expensetracker.domain.features.accounts.use_cases

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserAccountsUseCaseTest {

    @Test
    fun `On invoke should return user accounts from accounts repository`() {
        val expectedAccounts = listOf<Account>()
        val accountsRepository: AccountsRepository = mockk {
            coEvery { getUserAccounts() } returns expectedAccounts
        }
        val getUserAccountsUseCase = GetUserAccountsUseCase(accountsRepository)

        val resultAccounts = runBlocking { getUserAccountsUseCase() }

        coVerify { accountsRepository.getUserAccounts() }
        assertEquals(expectedAccounts, resultAccounts)
    }
}
