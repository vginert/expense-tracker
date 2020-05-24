package com.vginert.expensetracker.domain.features.transactions

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.categories.Category
import java.util.Date

data class Transaction(
    val id: Int,
    val account: Account,
    val category: Category,
    val amount: Float,
    val time: Date
)
