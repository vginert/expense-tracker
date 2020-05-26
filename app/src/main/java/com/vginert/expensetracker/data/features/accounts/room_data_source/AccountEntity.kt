package com.vginert.expensetracker.data.features.accounts.room_data_source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vginert.expensetracker.domain.features.accounts.Account

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) {
    fun toDomain() = Account(
        id,
        name
    )
}
