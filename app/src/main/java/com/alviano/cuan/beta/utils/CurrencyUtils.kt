package com.alviano.cuan.beta.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun formatAsCurrency(amount: Long): String {
    val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    format.currency = Currency.getInstance("IDR")
    format.maximumFractionDigits = 0
    return format.format(amount)
}