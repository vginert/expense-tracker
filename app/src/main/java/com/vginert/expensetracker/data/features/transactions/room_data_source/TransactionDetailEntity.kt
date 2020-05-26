package com.vginert.expensetracker.data.features.transactions.room_data_source

import androidx.room.Embedded
import androidx.room.Relation
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.domain.features.transactions.Transaction
import java.util.*

data class TransactionDetailEntity(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(parentColumn = "categoryId", entityColumn = "id")
    val category: CategoryEntity
) {
    fun toDomain() = Transaction(
        transaction.id,
        category.toDomain(),
        transaction.amount,
        Date(transaction.time)
    )
}
