package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.categories.Category
import com.vginert.expensetracker.presentation.core.models.CategoryModel.Type

class CategoryModel(
    val id: Int,
    val name: String,
    val type: Type
) {

    override fun toString(): String = name

    enum class Type {
        EXPENSE,
        INCOME;
    }
}

fun Category.toPresentation() = CategoryModel(
    id,
    name,
    type.toPresentation()
)

fun Category.Type.toPresentation() = when (this) {
    Category.Type.EXPENSE -> Type.EXPENSE
    Category.Type.INCOME -> Type.INCOME
}