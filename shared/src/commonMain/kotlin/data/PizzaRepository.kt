package data

import data.model.Order
import data.model.PizzaProduct
import data.model.PizzaSelected
import database.Checkout
import kotlinx.coroutines.flow.Flow
import presentation.model.NewMessage

interface PizzaRepository {

    suspend fun getProducts(): List<PizzaProduct>

    suspend fun getCheckoutItems(): Flow<List<Checkout>>

    suspend fun saveCheckoutItem(item: PizzaSelected)

    suspend fun deleteCheckoutItem(item: PizzaSelected)

    suspend fun getMessages(): Flow<NewMessage>

    suspend fun sendOrder(selectionUi: Order, acceptedOrder: (Order) -> Unit)
}