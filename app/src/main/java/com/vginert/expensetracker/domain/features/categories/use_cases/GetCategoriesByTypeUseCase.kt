package com.vginert.expensetracker.domain.features.categories.use_cases

import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.Category

class GetCategoriesByTypeUseCase(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(type: Category.Type): List<Category> = categoriesRepository.getCategoriesFromType(type)
}
