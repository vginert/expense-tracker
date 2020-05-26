package com.vginert.expensetracker.data.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountEntity
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountsDao
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoriesDao
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.data.features.transaction.room_data_source.TransactionsDao
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity

@Database(
    entities = [AccountEntity::class, CategoryEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = true
)
abstract class ExpenseTrackerDatabase : RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun transactionsDao(): TransactionsDao
}