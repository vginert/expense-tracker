package com.vginert.expensetracker.data.features.accounts

import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountDetailsEntity
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountEntity
import com.vginert.expensetracker.data.features.accounts.room_data_source.AccountsDao
import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.AccountDetail
import com.vginert.expensetracker.domain.features.accounts.AccountsRepository

class AccountsDataRepository(
    private val accountsDao: AccountsDao
) : AccountsRepository {

    override suspend fun getUserAccounts(): List<Account> =
        accountsDao.getAll().map(AccountEntity::toDomain)

    override suspend fun getAccountsDetails(): List<AccountDetail> =
        accountsDao.getAccountsDetails().map(AccountDetailsEntity::toDomain)
}
