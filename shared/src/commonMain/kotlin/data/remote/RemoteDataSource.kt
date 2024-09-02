package data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.ContentType
import kotlinx.coroutines.flow.Flow
import presentation.model.Driver
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi

class RemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getProducts(): List<PizzaProductUi> {
        return httpClient.get("products") {
            accept(ContentType.Application.Json)
        }.body()
    }

    suspend fun getDriver(): Driver {
        return httpClient.get("driver").body()
    }

    suspend fun getMapDirections(): MapDirections {
        return httpClient.get("directions").body()
    }

    suspend fun getMessages(): Flow<Message> {
        return httpClient.get("messages").body()
    }

    suspend fun sendOrder(selectionUi: List<PizzaProductUi>) {
        return httpClient.post("order").body()
    }
}