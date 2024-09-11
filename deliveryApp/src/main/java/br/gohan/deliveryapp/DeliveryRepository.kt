package br.gohan.deliveryapp

import api.ApiRoutes
import api.EMULATOR_IP
import data.model.Order
import data.model.Orders
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.sse.SSE
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json

class DeliveryRepository {
    private lateinit var httpClient: HttpClient

    fun setHttpClient() {
        httpClient = HttpClient(CIO) {
            install(SSE)
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    suspend fun observeOrders(
        orders: MutableStateFlow<Orders>,
        acceptOrder: MutableSharedFlow<Order>
    ) {
        httpClient.webSocket(
            path = ApiRoutes.DeliverWebSocket.route,
            method = HttpMethod.Get,
            host = EMULATOR_IP,
            port = 8080
        ) {
            val ordersList = receiveDeserialized<Orders>()
            /*
            orders.update {
                ordersList
            }

            acceptOrder.collect {
                sendSerialized(it)
            }*/
        }
    }
}