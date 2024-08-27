package presentation.model

import data.remote.Products
import kotlin.jvm.JvmInline


@JvmInline
value class ProductsState(val products: List<PizzaProductUi>? = null)
