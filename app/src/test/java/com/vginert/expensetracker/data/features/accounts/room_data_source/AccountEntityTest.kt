package com.vginert.expensetracker.data.features.accounts.room_data_source

import org.junit.Assert.assertEquals
import org.junit.Test

class AccountEntityTest {

    companion object {
        private const val ID = 12346
        private const val NAME = "Account for tests"
    }

    @Test
    fun `On transform to domain should return correct account`() {
        val accountEntity = AccountEntity(12346, "Account for tests")

        val account = accountEntity.toDomain()

        assertEquals(ID, account.id)
        assertEquals(NAME, account.name)
    }
}