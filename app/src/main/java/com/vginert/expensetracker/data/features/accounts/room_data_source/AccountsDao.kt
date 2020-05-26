package com.vginert.expensetracker.data.features.accounts.room_data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts")
    fun getAll(): List<AccountEntity>

    @Transaction
    @Query("SELECT * FROM accounts")
    fun getAccountsDetails(): List<AccountDetailsEntity>
}
