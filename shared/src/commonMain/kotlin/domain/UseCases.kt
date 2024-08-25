package domain

internal const val CURRENT_DISCOUNT = 0.2

internal fun Double.setDiscount(discount: Double): String {
    return this.times(1 - discount).toCurrency()
}