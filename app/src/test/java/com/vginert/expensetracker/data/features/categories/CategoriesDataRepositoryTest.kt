package com.vginert.expensetracker.data.features.categories

import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoriesDao
import com.vginert.expensetracker.data.features.categories.room_data_sources.CategoryEntity
import com.vginert.expensetracker.domain.features.categories.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CategoriesDataRepositoryTest {

    @Test
    fun `On get categories from type should return category list from categories dao`() {
        val expectedCategory: Category = mockk()
        val categoryEntity: CategoryEntity = mockk {
            every { toDomain() } returns expectedCategory
        }
        val categoriesDao: CategoriesDao = mockk() {
            coEvery { getByType(any()) } returns listOf(categoryEntity)
        }
        val categoriesDataRepository = CategoriesDataRepository(categoriesDao)

        val categoriesResult = runBlocking {
            categoriesDataRepository.getCategoriesFromType(Category.Type.EXPENSE)
        }

        coVerify { categoriesDao.getByType(CategoryEntity.EXPENSE_TYPE) }
        assertTrue(categoriesResult.size == 1)
        assertEquals(expectedCategory, categoriesResult[0])
    }
}