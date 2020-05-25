package com.vginert.expensetracker.presentation.features.dashboard

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.epoxy.KotlinEpoxyHolder
import com.vginert.expensetracker.presentation.core.format.currencyFormat
import com.vginert.expensetracker.presentation.core.models.CategoryModel
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
    lateinit var type: CategoryModel.Type

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.categoryNameTextView.text = categoryName
        holder.view.amountTextView.currencyFormat(amount)
        renderTime(holder.view)
        renderTransactionType(holder.view)
    }

    private fun renderTime(view: View) {
        view.timeTextView.text =
            DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(time)
    }

    private fun renderTransactionType(view: View) {
        val imageResource = when (type) {
            CategoryModel.Type.EXPENSE -> R.drawable.ic_remove_circle_outline
            CategoryModel.Type.INCOME -> R.drawable.ic_add_circle_outline
        }
        view.transactionTypeImageView.setImageResource(imageResource)
    }
}
