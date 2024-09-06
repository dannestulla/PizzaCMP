package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val pizzas: List<String>,
    val numberOfProducts: Int,
    val address: String? = null,
    val addressPosition: Position,
    val driverResponsible: Driver? = null,
    val estimatedTime: String? = null,
    val deliveryFee: String? = null,
)
