package presentation.model

import data.model.PizzaProduct
import kotlin.jvm.JvmInline


@JvmInline
value class ProductsState(val products: List<PizzaProduct>? = null)
