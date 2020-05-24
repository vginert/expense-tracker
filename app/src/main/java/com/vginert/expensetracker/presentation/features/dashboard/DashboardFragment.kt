package com.vginert.expensetracker.presentation.features.dashboard

import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.fragments.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment() {

    override val viewModel: DashboardViewModel by viewModel()
    override val layout: Int = R.layout.dashboard_fragment
}
