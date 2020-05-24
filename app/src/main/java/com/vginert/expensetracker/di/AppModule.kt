package com.vginert.expensetracker.di

import com.vginert.expensetracker.data.features.accounts.AccountsDataRepository
import com.vginert.expensetracker.data.features.categories.CategoriesDataRepository
import com.vginert.expensetracker.data.features.transactions.TransactionsDataRepository
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository
import com.vginert.expensetracker.domain.features.accounts.use_cases.GetUserAccountsUseCase
import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.use_cases.GetCategoriesByTypeUseCase
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository
import com.vginert.expensetracker.domain.features.transactions.use_cases.CreateTransactionUseCase
import org.koin.dsl.module

val appModule = module {

    // region Repositories
    single<AccountsRepository> { AccountsDataRepository() }
    single<CategoriesRepository> { CategoriesDataRepository() }
    single<TransactionsRepository> { TransactionsDataRepository() }
    // endregion

    // region Use Cases
    factory { GetUserAccountsUseCase(get()) }
    factory { GetCategoriesByTypeUseCase(get()) }
    factory { CreateTransactionUseCase(get()) }
    // endregion
}
