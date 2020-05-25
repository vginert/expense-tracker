package com.vginert.expensetracker.domain.features.accounts.use_cases

import com.vginert.expensetracker.domain.features.accounts.AccountDetail
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository

class GetAccountsDetailsUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(): List<AccountDetail> = accountsRepository.getAccountsDetails()
}
