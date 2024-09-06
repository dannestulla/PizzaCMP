package data.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaProduct(
    val image: String? = null,
    val name: String,
    val description: String,
    val toppings: List<String>,
    val prices: List<Double>
)

