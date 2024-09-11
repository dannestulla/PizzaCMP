package domain

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

actual fun Double.toCurrency(): String {
    val nsNumberFormatter by lazy { NSNumberFormatter() }

    val formatter = nsNumberFormatter.apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        locale = NSLocale(localeIdentifier = "pt_BR")
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: "$this"
}

actual fun String.currencyToDouble(): Double {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        locale = NSLocale(localeIdentifier = "pt_BR")
    }
    return formatter.numberFromString(this)?.doubleValue ?: 0.0
}
