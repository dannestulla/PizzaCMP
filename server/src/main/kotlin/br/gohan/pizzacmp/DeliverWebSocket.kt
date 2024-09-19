package br.gohan.pizzacmp

import data.model.Order
import io.ktor.server.websocket.DefaultWebSocketServerSession
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

suspend fun DefaultWebSocketServerSession.setDeliverWebSocket(
    mainOrders: MutableStateFlow<List<Order>>,
    acceptedOrder: MutableSharedFlow<Order>,
    logging: MutableSharedFlow<String>
) {

}