package com.vginert.expensetracker.data.features.categories

import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoriesDao
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.data.features.categories.room_data_sources.toRoomEntity
import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.Category

class CategoriesDataRepository(
    private val categoriesDao: CategoriesDao
) : CategoriesRepository {

    override suspend fun getCategoriesFromType(type: Category.Type): List<Category> =
        categoriesDao.getByType(type.toRoomEntity()).map(CategoryEntity::toDomain)
}
