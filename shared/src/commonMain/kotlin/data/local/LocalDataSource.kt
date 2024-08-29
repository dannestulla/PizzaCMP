package data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.gohan.pizzacmp.database.PizzaDatabase
import database.Checkout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import presentation.model.PizzaProductUi

class LocalDataSource(
    database: PizzaDatabase,
) {
    private val checkoutQueries = database.checkoutQueries

    fun getItems(): Flow<List<Checkout>> {
        return checkoutQueries
            .selectAllProducts()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun saveItem(productUi: PizzaProductUi) {
        checkoutQueries.insertProduct(
            image = productUi.image,
            name = productUi.name,
            description = productUi.description,
            toppings = Json.encodeToString(productUi.toppings),
            prices = Json.encodeToString(productUi.prices),
            priceSelected = productUi.priceSelected,
            sizeSelected = Json.encodeToString(productUi.sizeSelected)
        )
    }

    fun deleteItem(name: String) {
        checkoutQueries.deleteProduct(name)
    }
}