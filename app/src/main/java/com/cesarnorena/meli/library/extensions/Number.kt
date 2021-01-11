package com.cesarnorena.meli.library.extensions

import java.text.NumberFormat
import java.util.Currency

fun Number.toCurrencyFormat(currencyCode: String): String {
    val currency = Currency.getInstance(currencyCode)
    val formatter = NumberFormat.getCurrencyInstance()
    formatter.currency = currency
    return formatter.format(this)
}
