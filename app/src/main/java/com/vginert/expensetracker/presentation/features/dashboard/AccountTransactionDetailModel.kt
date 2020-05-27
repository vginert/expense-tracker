package com.vginert.expensetracker.presentation.features.dashboard

import androidx.annotation.DrawableRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.epoxy.KotlinEpoxyHolder
import com.vginert.expensetracker.presentation.core.format.currencyFormat
import kotlinx.android.synthetic.main.account_transaction_detail_item.view.*
import java.text.DateFormat
import java.util.*

@EpoxyModelClass(layout = R.layout.account_transaction_detail_item)
abstract class AccountTransactionDetailModel :
    EpoxyModelWithHolder<KotlinEpoxyHolder>() {

    @EpoxyAttribute
    lateinit var categoryName: String

    @EpoxyAttribute
    var amount: Float = 0F

    @EpoxyAttribute
    lateinit var time: Date

    @EpoxyAttribute
    @DrawableRes
    var icon: Int = R.drawable.ic_help_outline

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.categoryNameTextView.text = categoryName
        holder.view.amountTextView.currencyFormat(amount)
        holder.view.timeTextView.text =
            DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(time)
        holder.view.transactionTypeImageView.setImageResource(icon)
    }
}
