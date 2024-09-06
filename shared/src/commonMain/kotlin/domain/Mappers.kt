package domain

import data.model.Order
import data.model.PizzaProduct
import data.model.PizzaSelected
import data.model.PizzaSize
import data.model.Position
import database.Checkout
import kotlinx.serialization.json.Json

fun List<Checkout>.toProductUi(): List<PizzaSelected> =
    this.map {
        PizzaSelected(
            image = it.image,
            name = it.name,
            description = it.description,
            toppingsSelected = Json.decodeFromString(it.toppings),
            sizeSelected = Json.decodeFromString(it.sizeSelected),
            priceSelected = it.priceSelected.toDouble()
        )
    }

fun PizzaProduct.toSelected(
    toppingsSelected: Map<String, Int>? = null,
    sizeSelected: PizzaSize? = null,
    priceSelected: Double? = null
): PizzaSelected =
    PizzaSelected(
        image = this.image,
        name = this.name,
        description = this.description,
        toppingsSelected = toppingsSelected ?: mapOf(),
        sizeSelected = sizeSelected ?: PizzaSize.Medium,
        priceSelected = priceSelected ?: this.prices[1]
    )

fun List<PizzaSelected>.toOrder(gpsPosition: Position): Order {
    val pizzas = this.map { it.name }
    return Order(
        pizzas = pizzas,
        numberOfProducts = this.size,
        addressPosition = gpsPosition,
    )
}
