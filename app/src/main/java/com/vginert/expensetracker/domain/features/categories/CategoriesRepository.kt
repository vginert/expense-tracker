package com.vginert.expensetracker.domain.features.categories

interface CategoriesRepository {
    suspend fun getCategoriesFromType(type: Category.Type): List<Category>
}
