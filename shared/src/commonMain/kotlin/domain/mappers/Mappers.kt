package domain.mappers

import database.Checkout
import kotlinx.serialization.json.Json
import presentation.model.PizzaProductUi

fun List<Checkout>.toProductUi(): List<PizzaProductUi> =
    this.map {
        PizzaProductUi(
            image = it.image,
            name = it.name,
            description = it.description,
            toppings = Json.decodeFromString(it.toppings),
            prices = Json.decodeFromString(it.prices),
            sizeSelected = Json.decodeFromString(it.sizeSelected),
            priceSelected = it.priceSelected
        )
    }
