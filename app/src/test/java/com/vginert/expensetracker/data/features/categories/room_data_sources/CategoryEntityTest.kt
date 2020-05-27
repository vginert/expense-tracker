package com.vginert.expensetracker.data.features.categories.room_data_sources

import com.vginert.expensetracker.domain.features.categories.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryEntityTest {

    companion object {
        private const val ID = 12354
        private const val NAME = "Category for tests"
    }

    @Test
    fun `On transform to domain should return correct category`() {
        val categoryEntity = CategoryEntity(ID, NAME, CategoryEntity.EXPENSE_TYPE)

        val category = categoryEntity.toDomain()

        assertEquals(ID, category.id)
        assertEquals(NAME, category.name)
        assertEquals(Category.Type.EXPENSE, category.type)
    }

    @Test
    fun `On transform to domain should return correct category type when is income`() {
        val categoryEntity = CategoryEntity(ID, NAME, CategoryEntity.INCOME_TYPE)

        val category = categoryEntity.toDomain()

        assertEquals(Category.Type.INCOME, category.type)
    }

    @Test
    fun `On transform category type to room entity should return correct type is income`() {
        val categoryIncomeType = Category.Type.INCOME
        val categoryExpenseType = Category.Type.EXPENSE

        val categoryIncomeEntityType = categoryIncomeType.toRoomEntity()
        val categoryExpenseEntityType = categoryExpenseType.toRoomEntity()

        assertEquals(CategoryEntity.INCOME_TYPE, categoryIncomeEntityType)
        assertEquals(CategoryEntity.EXPENSE_TYPE, categoryExpenseEntityType)
    }
}