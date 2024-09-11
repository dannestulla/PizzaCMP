package data.remote

import api.ApiRoutes
import api.EMULATOR_IP
import data.model.Order
import data.model.PizzaProduct
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.coroutines.flow.Flow
import presentation.model.NewMessage

class RemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getProducts(): List<PizzaProduct> {
        return httpClient.get(ApiRoutes.Products.route) {
            accept(ContentType.Application.Json)
        }.body()
    }

    suspend fun getMessages(): Flow<NewMessage> {
        return httpClient.get("messages").body()
    }

    suspend fun sendOrder(selectionUi: Order) {
        // Creates client WebSocket session
        httpClient.webSocket(
            path = ApiRoutes.ClientWebSocket.route,
            host = EMULATOR_IP,
            port = 8080
        ) {
            sendSerialized(selectionUi)
        }
    }
}