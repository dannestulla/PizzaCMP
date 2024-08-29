package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaProductUi(
    val image: String? = null,
    val name: String,
    val description: String,
    val toppings: Map<String, Int>,
    val prices: List<Double>,
    val sizeSelected: PizzaSize? = PizzaSize.Medium,
    val priceSelected: Double? = prices[1],
)

