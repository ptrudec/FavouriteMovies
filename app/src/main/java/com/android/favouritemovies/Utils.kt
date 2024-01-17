package com.android.favouritemovies

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Created by petar.tomorad-rudec on 16/01/2024
 */

object Utils {
    fun formatNumberWithDecimalSeparator(number: Number): String {
        val decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
        decimalFormatSymbols.groupingSeparator = '.'

        val decimalFormat = DecimalFormat("#,##0", decimalFormatSymbols)

        return decimalFormat.format(number.toDouble())
    }

    fun formatDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        val date = LocalDate.parse(inputDate, inputFormatter)
        return date.format(outputFormatter)
    }
}