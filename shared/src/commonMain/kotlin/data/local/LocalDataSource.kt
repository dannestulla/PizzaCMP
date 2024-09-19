package data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.gohan.pizzacmp.database.PizzaDatabase
import data.model.Order
import data.model.PizzaSelected
import data.model.Position
import database.Checkout
import domain.toCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalDataSource(
    database: PizzaDatabase
) {
    private val checkoutQueries = database.checkoutQueries
    private val orderQueries = database.ordersQueries

    fun getItems(): Flow<List<Checkout>> {
        return checkoutQueries
            .selectAll()
              .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun saveItem(productUi: PizzaSelected) {
        checkoutQueries.insert(
             image = productUi.image,
             name = productUi.name,
             description = productUi.description,
            toppings = Json.encodeToString(
                MapSerializer(String.serializer(), Int.serializer()),
                productUi.toppingsSelected
            ),
            priceSelected = productUi.priceSelected.toCurrency(),
            sizeSelected = productUi.sizeSelected
        )
    }

    fun deleteItem(name: String) {
        checkoutQueries.delete(name)
    }

    fun saveOrder(order: Order) {
        orderQueries.insert(
            pizzas = order.pizzas.toString(),
            address = null,
            addressPosition = Json.encodeToString<Position>(order.addressPosition),
            driverResponsible = null,
            estimatedTime = null,
            deliveryFee = null
        )
    }
}