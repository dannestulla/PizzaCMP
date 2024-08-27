package presentation.model

data class PizzaProductUi(
    val image: String? = null,
    val name: String,
    val description: String,
    val price: Double,
    var toppings: Map<String, Int>,
)

