package com.vginert.expensetracker.domain.features.accounts.use_cases

import com.vginert.expensetracker.domain.features.accounts.AccountDetail
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAccountsDetailsUseCaseTest {

    @Test
    fun `On invoke should return accounts details from accounts repository`() {
        val expectedAccountsDetails = listOf<AccountDetail>()
        val accountsRepository: AccountsRepository = mockk {
            coEvery { getAccountsDetails() } returns expectedAccountsDetails
        }
        val getAccountsDetailsUseCase = GetAccountsDetailsUseCase(accountsRepository)

        val resultAccountsDetails = runBlocking { getAccountsDetailsUseCase() }

        coVerify { accountsRepository.getAccountsDetails() }
        assertEquals(expectedAccountsDetails, resultAccountsDetails)
    }
}
