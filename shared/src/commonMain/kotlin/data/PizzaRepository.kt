package data

import data.remote.Products
import kotlinx.coroutines.flow.Flow
import presentation.model.Driver
import presentation.model.ExampleUi
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi

interface PizzaRepository {

    suspend fun getProducts(): List<PizzaProductUi>

    suspend fun getProduct(): PizzaProductUi

    suspend fun getCheckoutItems(): List<PizzaProductUi>

    suspend fun getDriver(): Driver

    suspend fun getMapDirections(): MapDirections

    suspend fun getMessages(): Flow<Message>
    /*    fun saveExample(product: ExampleUi, discount: Double)

        fun removeExample(product: ExampleUi)*/

  }