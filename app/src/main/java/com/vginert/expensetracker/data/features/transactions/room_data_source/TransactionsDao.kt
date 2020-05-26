package com.vginert.expensetracker.data.features.transaction.room_data_source

import androidx.room.Dao
import androidx.room.Insert
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity

@Dao
interface TransactionsDao {

    @Insert
    fun insert(vararg transaction: TransactionEntity)
}