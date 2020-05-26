package com.vginert.expensetracker.di

import androidx.room.Room
import com.vginert.expensetracker.data.core.database.ExpenseTrackerDatabase
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
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


private const val DATABASE_NAME = "expense-tracker"

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

    // region Database
    single {
        Room.databaseBuilder(
            androidContext(),
            ExpenseTrackerDatabase::class.java,
            DATABASE_NAME
        ).createFromAsset("$DATABASE_NAME.db").build()
    }
    single { get<ExpenseTrackerDatabase>().accountsDao() }
    single { get<ExpenseTrackerDatabase>().categoriesDao() }
    single { get<ExpenseTrackerDatabase>().transactionsDao() }
    // endregion

    // region Repositories
    single<AccountsRepository> { AccountsDataRepository(get()) }
    single<CategoriesRepository> { CategoriesDataRepository(get()) }
    single<TransactionsRepository> { TransactionsDataRepository(get()) }
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
