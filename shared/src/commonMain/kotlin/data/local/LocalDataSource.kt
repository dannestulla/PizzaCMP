package data.local

import database.Checkout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import presentation.model.PizzaProductUi

class LocalDataSource {
    //private val checkoutQueries = database.checkoutQueries

    fun getItems(): Flow<List<Checkout>> {
        return flowOf()

        /*  checkoutQueries
              .selectAllProducts()
              .asFlow()
              .mapToList(Dispatchers.IO)*/
    }

    fun saveItem(productUi: PizzaProductUi) {
        /* checkoutQueries.insertProduct(
             image = productUi.image,
             name = productUi.name,
             description = productUi.description,
             toppings = Json.encodeToString(productUi.toppings),
             prices = Json.encodeToString(productUi.prices),
             priceSelected = productUi.priceSelected,
             sizeSelected = Json.encodeToString(productUi.sizeSelected)
         )*/
    }

    fun deleteItem(name: String) {
        //checkoutQueries.deleteProduct(name)
    }
}