package com.vginert.expensetracker.data.features.categories

import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoriesDao
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.data.features.categories.room_data_sources.toRoomEntity
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

class CategoriesDataRepository(
    private val categoriesDao: CategoriesDao
) : CategoriesRepository {

    override suspend fun getCategoriesFromType(type: Category.Type): List<Category> =
        categoriesDao.getByType(type.toRoomEntity()).map(CategoryEntity::toDomain)
}
