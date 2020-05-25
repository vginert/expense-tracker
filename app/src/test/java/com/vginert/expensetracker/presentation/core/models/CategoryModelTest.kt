package com.vginert.expensetracker.presentation.core.models

import com.vginert.expensetracker.domain.features.categories.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryModelTest {

    companion object {
        private const val CATEGORY_ID = 12343213
        private const val CATEGORY_NAME = "Account test name"
        private val CATEGORY_TYPE = Category.Type.INCOME
        private val CATEGORY_MODEL_TYPE = CategoryModel.Type.INCOME
    }

    @Test
    fun `On transform category to presentation should return correct category model`() {
        val category = Category(CATEGORY_ID, CATEGORY_NAME, CATEGORY_TYPE)

        val categoryModel = category.toPresentation()

        assertEquals(CATEGORY_ID, categoryModel.id)
        assertEquals(CATEGORY_NAME, categoryModel.name)
        assertEquals(CATEGORY_MODEL_TYPE, categoryModel.type)
    }
}