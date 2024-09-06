package data

import data.model.Order
import data.model.PizzaProduct
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import presentation.model.DriverState
import presentation.model.MapDirectionsState
import presentation.model.NewMessage

class RemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getProducts(): List<PizzaProduct> {
        return httpClient.get("products") {
            accept(ContentType.Application.Json)
        }.body()
    }

    suspend fun getDriver(): DriverState {
        return httpClient.get("driver").body()
    }

    suspend fun getMapDirections(): MapDirectionsState {
        return httpClient.get("directions").body()
    }

    suspend fun getMessages(): Flow<NewMessage> {
        return httpClient.get("messages").body()
    }

    suspend fun sendOrder(selectionUi: Order) {
        httpClient.post("order") {
            contentType(ContentType.Application.Json)
            setBody {
                selectionUi
            }
        }
    }
}