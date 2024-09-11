package br.gohan.pizzacmp

import data.model.Order
import data.model.Orders
import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.sendSerialized
import io.ktor.websocket.Frame
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

fun DefaultWebSocketServerSession.setDeliverWebSocket(
    mainOrders: MutableStateFlow<List<Order>>,
    acceptedOrder: MutableSharedFlow<Order>
) {
    launch {
        // When order is created by client, fill the list in the delivery app
        mainOrders.collect { orders ->
            if (mainOrders.value.isEmpty()) {
                send(Frame.Text("no-order"))
            } else {
                sendSerialized(Orders(orders))
            }
            delay(3000)
        }
    }

    launch {
        // When delivery app accepts order
        val orderReceived = receiveDeserialized<Order>()
        acceptedOrder.emit(orderReceived)
    }

    launch {
        // When an order is accepted by delivery, client is updated
        acceptedOrder.collect { order ->
            mainOrders.update {
                // Delete from list
                mainOrders.value.toMutableList().filterNot { it == order }
            }
            sendSerialized(order)
        }
    }
}