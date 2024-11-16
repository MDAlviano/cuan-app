package com.alviano.cuan.beta.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun formatAsCurrency(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    format.currency = Currency.getInstance("IDR")
    format.maximumFractionDigits = 0
    return format.format(amount)
}