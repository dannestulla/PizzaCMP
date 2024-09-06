package data.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaSelected(
    val image: String? = null,
    val name: String,
    val description: String,
    val toppingsSelected: Map<String, Int>,
    val sizeSelected: PizzaSize,
    val priceSelected: Double,
)
