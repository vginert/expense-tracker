package com.vginert.expensetracker.presentation.features.transaction

import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.fragments.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionFragment : BaseFragment() {

    override val viewModel: TransactionViewModel by viewModel()
    override val layout: Int = R.layout.transaction_fragment
}
