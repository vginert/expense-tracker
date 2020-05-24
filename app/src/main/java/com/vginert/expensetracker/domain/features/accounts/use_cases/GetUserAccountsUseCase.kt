package com.vginert.expensetracker.domain.features.accounts.use_cases

import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository

class GetUserAccountsUseCase(
    private val accountRepository: AccountsRepository
) {
    suspend operator fun invoke(): List<Account> = accountRepository.getUserAccounts()
}
