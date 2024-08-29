package domain.mappers

import database.Checkout
import kotlinx.serialization.json.Json
import presentation.model.ExampleUi
import presentation.model.PizzaProductUi

fun List<ExampleUi>.toProduct(): List<ExampleUi> =
    this.map {
        ExampleUi(
            title = it.title,
            oldPrice = it.oldPrice,
            newPrice = it.newPrice,
            description = it.description,
            images = listOf(it.title),
            discount = it.discount,
            category = it.category
        )
    }

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
