package domain

import java.text.NumberFormat
import java.util.Locale

actual fun Double.toCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}

actual fun Double.toPercentage(): String {
    return NumberFormat.getPercentInstance().apply {
        maximumFractionDigits = 2
    }.format(this) + "OFF"
}

actual fun String.percentageToDouble(): Double {
    return this.removeSuffix("%").toDouble() / 100.0
}

actual fun String.currencyToDouble(): Double {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val number = format.parse(this)
    return number.toDouble()
}
