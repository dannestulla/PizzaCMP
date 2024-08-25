package domain

import platform.Foundation.*

actual fun Double.toCurrency(): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        locale = NSLocale(localeIdentifier = "pt_BR")
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: "$this"
}

actual fun Double.toPercentage(): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterPercentStyle
        maximumFractionDigits = 2 // Define a quantidade de casas decimais, se desejado
    }
    return formatter.stringFromNumber(NSNumber(this)) ?: "$this%"
}

actual fun String.percentageToDouble(): Double {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterPercentStyle
    }
    return formatter.numberFromString(this)?.doubleValue ?: 0.0
}

actual fun String.currencyToDouble(): Double {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
        locale = NSLocale(localeIdentifier = "pt_BR")
    }
    return formatter.numberFromString(this)?.doubleValue ?: 0.0
}
