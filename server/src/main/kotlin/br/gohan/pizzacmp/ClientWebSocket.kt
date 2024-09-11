package br.gohan.pizzacmp

import data.model.Order
import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.server.websocket.receiveDeserialized
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

fun DefaultWebSocketServerSession.setClientWebSocket(
    mainOrders: MutableStateFlow<List<Order>>,
) {
    launch {
        val order = receiveDeserialized<Order>()
        mainOrders.update {
            val newOrders = mainOrders.value.toMutableList()
            newOrders.add(order)
            newOrders
        }
    }
}