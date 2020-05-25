package com.vginert.expensetracker.di

import com.vginert.expensetracker.data.core.exceptions.handlers.ExceptionHandlersWrapper
import com.vginert.expensetracker.data.core.exceptions.handlers.FirebaseExceptionHandler
import com.vginert.expensetracker.data.core.exceptions.handlers.LogExceptionHandler
import com.vginert.expensetracker.data.core.exceptions.handlers.SentryExceptionHandler
import com.vginert.expensetracker.data.features.accounts.AccountsDataRepository
import com.vginert.expensetracker.data.features.categories.CategoriesDataRepository
import com.vginert.expensetracker.data.features.transactions.TransactionsDataRepository
import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository
import com.vginert.expensetracker.domain.features.accounts.use_cases.GetAccountsDetailsUseCase
import com.vginert.expensetracker.domain.features.accounts.use_cases.GetUserAccountsUseCase
import com.vginert.expensetracker.domain.features.categories.CategoriesRepository
import com.vginert.expensetracker.domain.features.categories.use_cases.GetCategoriesByTypeUseCase
import com.vginert.expensetracker.domain.features.transactions.TransactionsRepository
import com.vginert.expensetracker.domain.features.transactions.use_cases.CreateTransactionUseCase
import com.vginert.expensetracker.presentation.features.dashboard.DashboardViewModel
import com.vginert.expensetracker.presentation.features.transaction.TransactionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // region Exception Handlers
    single<ExceptionHandler> {
        ExceptionHandlersWrapper(
            listOf(
                LogExceptionHandler(),
                FirebaseExceptionHandler(),
                SentryExceptionHandler()
            )
        )
    }
    // endregion

    // region Repositories
    single<AccountsRepository> { AccountsDataRepository() }
    single<CategoriesRepository> { CategoriesDataRepository() }
    single<TransactionsRepository> { TransactionsDataRepository() }
    // endregion

    // region Use Cases
    factory { GetUserAccountsUseCase(get()) }
    factory { GetAccountsDetailsUseCase(get()) }
    factory { GetCategoriesByTypeUseCase(get()) }
    factory { CreateTransactionUseCase(get()) }
    // endregion

    // region ViewModels
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { TransactionViewModel(get(), get(), get(), get()) }
    // endregion
}
