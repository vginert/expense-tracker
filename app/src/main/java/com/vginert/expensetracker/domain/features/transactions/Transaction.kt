package com.vginert.expensetracker.domain.features.transactions

import com.vginert.expensetracker.domain.features.categories.Category
import java.util.*

data class Transaction(
    val id: Int,
    val category: Category,
    val amount: Float,
    val time: Date
)
