package data.remote

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import mocks.driver
import mocks.mapDirections
import mocks.messages
import mocks.products
import presentation.model.Driver
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi

class RemoteDataSource(
    private val httpClient: HttpClient,
) {
    //private val baseUrl = "https://api.escuelajs.co/api/v1"

    suspend fun getProducts(): List<PizzaProductUi> {
        return products
        // return httpClient.get("$baseUrl/products").body()
    }

    fun getDriver(): Driver {
        return driver
    }

    fun getMapDirections(): MapDirections {
        return mapDirections
    }

    fun getMessages(): Flow<Message> {
        return flow { messages }
    }

    /*
        suspend fun getCategories(): List<Categories> {
            //return httpClient.get("$baseUrl/categories").body<List<Categories>>().subList(0, 5)
            // Sublist to avoid weird categories from API
        }*/
}