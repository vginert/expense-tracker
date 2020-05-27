package com.vginert.expensetracker.domain.features.categories

data class Category(
    val id: Int,
    val name: String,
    val type: Type,
    val icon: Int = -1
) {

    enum class Type {
        EXPENSE,
        INCOME
    }
}
