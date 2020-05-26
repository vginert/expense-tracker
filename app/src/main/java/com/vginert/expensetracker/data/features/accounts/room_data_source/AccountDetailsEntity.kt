package com.vginert.expensetracker.data.features.accounts.room_data_source

import androidx.room.Embedded
import androidx.room.Relation
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionDetailEntity
import com.vginert.expensetracker.data.features.transactions.room_data_source.TransactionEntity
import com.vginert.expensetracker.domain.features.accounts.AccountDetail

data class AccountDetailsEntity(
    @Embedded
    val account: AccountEntity,
    @Relation(parentColumn = "id", entityColumn = "accountId", entity = TransactionEntity::class)
    val transactions: List<TransactionDetailEntity>
) {

    fun toDomain() = AccountDetail(
        account.toDomain(),
        transactions.map { it.toDomain() },
        transactions.map { it.transaction.amount }.sum()
    )
}