package com.vginert.expensetracker.data.features.transactions.room_data_source

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountEntity
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import java.util.*

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("accountId")
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId")
        )],
    indices = [ Index("accountId"), Index("categoryId") ]
)
data class TransactionEntity(
    val accountId: Int,
    val categoryId: Int,
    val amount: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: Long = Date().time
)
