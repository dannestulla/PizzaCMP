package presentation.model

import kotlin.jvm.JvmInline

@JvmInline
value class CheckoutState(val products: List<PizzaProductUi>? = null)
