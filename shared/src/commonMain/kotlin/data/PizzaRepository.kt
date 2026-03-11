package data

import data.model.Driver
import data.model.MapDirections
import data.model.Message
import database.Checkout
import kotlinx.coroutines.flow.Flow
import presentation.model.PizzaProductUi

interface PizzaRepository {

    suspend fun getProducts(): List<PizzaProductUi>

    suspend fun getCheckoutItems(): Flow<List<Checkout>>

    suspend fun saveCheckoutItem(item: PizzaProductUi)

    suspend fun deleteCheckoutItem(item: PizzaProductUi)

    suspend fun getDriver(): Driver

    suspend fun getMapDirections(): MapDirections

    suspend fun getMessages(): Flow<Message>

    suspend fun sendOrder(selectionUi: List<PizzaProductUi>)
}