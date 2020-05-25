package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.accounts.Account
import org.junit.Assert.assertEquals
import org.junit.Test

class AccountModelTest {

    companion object {
        private const val ACCOUNT_ID = 12343213
        private const val ACCOUNT_NAME = "Account test name"
    }

    @Test
    fun `On transform account to presentation should return correct account model`() {
        val account = Account(ACCOUNT_ID, ACCOUNT_NAME)

        val accountModel = account.toPresentation()

        assertEquals(ACCOUNT_ID, accountModel.id)
        assertEquals(ACCOUNT_NAME, accountModel.name)
    }
}