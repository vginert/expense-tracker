package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.accounts.Account

data class AccountModel(
    val id: Int,
    val name: String
) {
    override fun toString(): String = name
}

fun Account.toPresentation() = AccountModel(
    id,
    name
)
