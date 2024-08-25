package domain.mappers

import presentation.model.ExampleUi

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
