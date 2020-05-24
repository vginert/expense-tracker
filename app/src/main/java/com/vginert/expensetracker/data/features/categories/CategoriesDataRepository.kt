package com.vginert.expensetracker.data.features.categories

import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.Category

val mockCategories = listOf(
    Category(0, "Tax", Category.Type.EXPENSE),
    Category(1, "Grocery", Category.Type.EXPENSE),
    Category(2, "Entertainment", Category.Type.EXPENSE),
    Category(3, "Gym", Category.Type.EXPENSE),
    Category(4, "Health", Category.Type.EXPENSE),
    Category(5, "Salary", Category.Type.INCOME),
    Category(6, "Dividends", Category.Type.INCOME)
)

class CategoriesDataRepository : CategoriesRepository {

    // TODO implement real repo, this is mock data for development.
    override suspend fun getCategoriesFromType(type: Category.Type): List<Category> =
        mockCategories.filter { it.type == type }
}
