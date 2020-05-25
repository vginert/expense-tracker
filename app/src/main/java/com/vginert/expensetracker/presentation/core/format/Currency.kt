package com.vginert.expensetracker.presentation.core.format

import android.widget.TextView
import com.vginert.expensetracker.R
import java.text.NumberFormat

fun TextView.currencyFormat(amount: Float) {
    val color = when {
        amount < 0 -> R.color.negative_balance
        amount > 0 -> R.color.positive_balance
        else -> R.color.neutral_balance
    }
    text = NumberFormat.getCurrencyInstance().format(amount)
    @Suppress("DEPRECATION") // TODO Remove when no needed api 21
    setTextColor(context.resources.getColor(color))
}