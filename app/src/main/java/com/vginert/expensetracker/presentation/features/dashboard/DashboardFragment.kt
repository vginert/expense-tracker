package com.vginert.expensetracker.presentation.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.fragments.BaseFragment
import com.vginert.expensetracker.presentation.core.lifecycle.observe
import com.vginert.expensetracker.presentation.core.lifecycle.observeEvent
import com.vginert.expensetracker.presentation.core.models.AccountDetailModel
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment() {

    override val viewModel: DashboardViewModel by viewModel()
    override val layout: Int = R.layout.dashboard_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent(viewModel.goToCreateTransaction) { navigateToCreateTransaction() }
        observe(viewModel.accountsDetails) { renderAccountsDetails(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createTransactionButton.setOnClickListener { viewModel.onCreateTransactionClick() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAccountsDetails()
    }

    private fun renderAccountsDetails(accountsDetails: List<AccountDetailModel>) {
        accountsDetailsRecyclerView.withModels {
            accountsDetails.forEach { accountDetails ->
                accountHeader {
                    id("header-${accountDetails.account.id}")
                    name(accountDetails.account.name)
                    balance(accountDetails.balance)
                }

                accountDetails.transactions.sortedByDescending { it.time }.forEach { transaction ->
                    accountTransactionDetail {
                        id("transaction-${transaction.id}")
                        categoryName(transaction.category.name)
                        amount(transaction.amount)
                        time(transaction.time)
                        type(transaction.category.type)
                    }
                }
            }
        }
    }

    private fun navigateToCreateTransaction() {
        val action = DashboardFragmentDirections.actionDashboardFragmentToTransactionFragment()
        findNavController().navigate(action)
    }
}
