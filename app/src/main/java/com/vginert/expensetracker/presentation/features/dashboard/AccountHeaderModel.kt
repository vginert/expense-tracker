package com.vginert.expensetracker.presentation.features.dashboard

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.epoxy.KotlinEpoxyHolder
import com.vginert.expensetracker.presentation.core.format.currencyFormat
import kotlinx.android.synthetic.main.account_header_item.view.*

@EpoxyModelClass(layout = R.layout.account_header_item)
abstract class AccountHeaderModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    var balance: Float = 0F

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.nameTextView.text = name
        holder.view.balanceTextView.currencyFormat(balance)
    }
}