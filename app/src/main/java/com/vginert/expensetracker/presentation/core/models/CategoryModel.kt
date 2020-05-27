package com.vginert.expensetracker.presentation.core.models

import androidx.annotation.DrawableRes
import com.vginert.expensetracker.R
import com.vginert.expensetracker.domain.features.categories.Category
import com.vginert.expensetracker.presentation.core.models.CategoryModel.Type

class CategoryModel(
    val id: Int,
    val name: String,
    val type: Type,
    @DrawableRes val icon: Int
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
    type.toPresentation(),
    iconToDrawableRes(icon)
)

fun Category.Type.toPresentation() = when (this) {
    Category.Type.EXPENSE -> Type.EXPENSE
    Category.Type.INCOME -> Type.INCOME
}

private fun iconToDrawableRes(icon: Int): Int = when (icon) {
    0 -> R.drawable.ic_attach_money
    1 -> R.drawable.ic_shopping_cart
    2 -> R.drawable.ic_movie
    3 -> R.drawable.ic_fitness_center
    4 -> R.drawable.ic_healing
    5 -> R.drawable.ic_work
    else -> R.drawable.ic_help_outline
}