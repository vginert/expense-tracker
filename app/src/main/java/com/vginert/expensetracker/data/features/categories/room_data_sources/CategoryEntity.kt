package com.vginert.expensetracker.data.features.categories.room_data_sources

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity.Companion.EXPENSE_TYPE
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity.Companion.INCOME_TYPE
import com.vginert.expensetracker.domain.features.categories.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: Int,
    val icon: Int = -1
) {

    companion object {
        const val EXPENSE_TYPE = 0
        const val INCOME_TYPE = 1
    }

    fun toDomain() = Category(
        id,
        name,
        typeToDomain(type),
        icon
    )

    private fun typeToDomain(type: Int) = when (type) {
        EXPENSE_TYPE -> Category.Type.EXPENSE
        INCOME_TYPE -> Category.Type.INCOME
        else -> throw IllegalArgumentException("Category Room Entity type not valid")
    }
}

fun Category.Type.toRoomEntity() = when (this) {
    Category.Type.EXPENSE -> EXPENSE_TYPE
    Category.Type.INCOME -> INCOME_TYPE
}