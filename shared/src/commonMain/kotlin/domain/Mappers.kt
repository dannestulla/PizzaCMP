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
            sizeSelected = it.sizeSelected,
            priceSelected = it.priceSelected.currencyToDouble()
        )
    }

fun PizzaProduct.toSelected(
    toppingsSelected: Map<String, Int>,
    sizeSelected: PizzaSize,
    priceSelected: Double
): PizzaSelected =
    PizzaSelected(
        image = this.image,
        name = this.name,
        description = this.description,
        toppingsSelected = toppingsSelected,
        sizeSelected = sizeSelected.name,
        priceSelected = priceSelected
    )

fun List<PizzaSelected>.toOrder(gpsPosition: Position): Order {
    val pizzas = this.map { it.name }
    return Order(
        pizzas = pizzas,
        numberOfProducts = this.size,
        addressPosition = gpsPosition,
        address = "Plaja del Carmen, 123" // TODO get user address from user register
    )
}
