package domain


expect fun Double.toCurrency(): String

expect fun String.currencyToDouble() : Double

expect fun String.percentageToDouble() : Double

expect fun Double.toPercentage() : String