package domain

import java.text.NumberFormat
import java.util.Locale

actual fun Double.toCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}

actual fun String.currencyToDouble(): Double {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val number = format.parse(this)
    return number.toDouble()
}
