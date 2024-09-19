package br.gohan.deliveryapp.data

import api.ApiRoutes
import api.EMULATOR_IP
import data.model.Order
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeliveryRepository(
    private val httpClient: HttpClient
) {
    suspend fun observeOrders(
        pendingOrders: MutableStateFlow<List<Order>>,
        acceptOrder: MutableSharedFlow<Order>
    ) {
        httpClient.webSocket(
            path = ApiRoutes.DeliverWebSocket.route,
            method = HttpMethod.Get,
            host = EMULATOR_IP,
            port = 8080
        ) {
            val acceptedOrder = launch {
                acceptOrder.collect { order ->
                    sendSerialized(order)
                }
            }

            runCatching {
                // Show pending orders
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val orders = receiveDeserialized<List<Order>>()
                        pendingOrders.update {
                            orders
                        }
                    }
                }
            }.onFailure { exception ->
                println("WebSocket exception: ${exception.localizedMessage}")
            }.also {
                acceptedOrder.cancel()
            }
        }
    }
}
