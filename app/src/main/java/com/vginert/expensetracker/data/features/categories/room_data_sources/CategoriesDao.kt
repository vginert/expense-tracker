package com.vginert.expensetracker.data.features.categories.room_data_sources

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories WHERE type = :type")
    fun getByType(type: Int): List<CategoryEntity>
}