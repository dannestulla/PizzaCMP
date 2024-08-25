/*
package data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import presentation.model.ExampleUi

class LocalDataSource(
    database: PizzaDatabase,
) {
    private val favoritesTable = database.favoritesQueries
    private val checkoutTable = database.checkoutQueries

    fun getExamples(): Flow<List<ExampleUi>> {
        return favoritesTable.getExamples()
            .asFlow()
            .mapToList(Dispatchers.IO)

    }

    fun saveExample(product: ExampleUi, discount: Double) {
        favoritesTable.saveExample(
            title = product.title,
            newPrice = product.newPrice,
            oldPrice = product.oldPrice,
            description = product.description,
            image = product.images.first(),
            timestamp = Clock.System.now().toEpochMilliseconds(),
            category = product.category,
            discount = discount.toString(),
        )
    }

    fun removeExample(product: ExampleUi) {
        val favorite = favoritesTable.getExampleTitle(product.title).executeAsList()
        favorite.forEach {
            favoritesTable.removeExampleById(it.id)
        }
    }
}*/
