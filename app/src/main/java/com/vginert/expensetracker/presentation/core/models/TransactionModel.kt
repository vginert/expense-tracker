package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.transactions.Transaction
import java.util.*

data class TransactionModel(
    val id: Int,
    val account: AccountModel,
    val category: CategoryModel,
    val amount: Float,
    val time: Date
)

fun Transaction.toPresentation() = TransactionModel(
    id,
    account.toPresentation(),
    category.toPresentation(),
    amount,
    time
)