package com.vginert.expensetracker.domain.features.categories.use_cases

import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCategoriesByTypeUseCaseTest {

    @Test
    fun `On invoke should return categories from categories repository`() {
        val type = Category.Type.INCOME
        val expectedCategories = listOf<Category>()
        val categoriesRepository: CategoriesRepository = mockk {
            coEvery { getCategoriesFromType(type) } returns expectedCategories
        }
        val getCategoriesByTypeUseCase = GetCategoriesByTypeUseCase(categoriesRepository)

        val resultCategories = runBlocking { getCategoriesByTypeUseCase(type) }

        coVerify { categoriesRepository.getCategoriesFromType(type) }
        assertEquals(expectedCategories, resultCategories)
    }
}
