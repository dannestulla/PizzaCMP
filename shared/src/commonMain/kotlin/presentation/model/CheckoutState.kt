package presentation.model

import data.model.PizzaSelected
import kotlin.jvm.JvmInline

@JvmInline
value class CheckoutState(val products: List<PizzaSelected>? = null)
