package data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.gohan.pizzacmp.database.PizzaDatabase
import data.model.PizzaSelected
import database.Checkout
import domain.toCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class LocalDataSource(
    database: PizzaDatabase
) {
    private val checkoutQueries = database.checkoutQueries

    fun getItems(): Flow<List<Checkout>> {
        return checkoutQueries
              .selectAllProducts()
              .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun saveItem(productUi: PizzaSelected) {
        checkoutQueries.insertProduct(
             image = productUi.image,
             name = productUi.name,
             description = productUi.description,
            toppings = Json.encodeToString(
                MapSerializer(String.serializer(), Int.serializer()),
                productUi.toppingsSelected
            ),
            priceSelected = productUi.priceSelected.toCurrency(),
            sizeSelected = productUi.sizeSelected.name
        )
    }

    fun deleteItem(name: String) {
        checkoutQueries.deleteProduct(name)
    }
}