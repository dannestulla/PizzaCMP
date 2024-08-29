package data

import database.Checkout
import kotlinx.coroutines.flow.Flow
import presentation.model.Driver
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi

interface PizzaRepository {

    suspend fun getProducts(): List<PizzaProductUi>

    suspend fun getCheckoutItems(): Flow<List<Checkout>>

    suspend fun saveCheckoutItem(item: PizzaProductUi)

    suspend fun deleteCheckoutItem(item: PizzaProductUi)

    suspend fun getDriver(): Driver

    suspend fun getMapDirections(): MapDirections

    suspend fun getMessages(): Flow<Message>

    fun sendOrder(selectionUi: List<PizzaProductUi>)
}