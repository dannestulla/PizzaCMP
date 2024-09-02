package presentation.model

import kotlin.jvm.JvmInline


@JvmInline
value class ProductsState(val products: List<PizzaProductUi>? = null)
