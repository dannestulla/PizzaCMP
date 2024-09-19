package br.gohan.pizzacmp

import api.ApiRoutes
import api.SERVER_IP
import api.SERVER_PORT
import data.model.Order
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.model.NewMessage

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = SERVER_IP,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    setConfig()

    val mainOrders = MutableStateFlow<List<Order>>(emptyList())
    val acceptedOrder = MutableSharedFlow<Order>()

    val chatMessages = MutableSharedFlow<NewMessage>()
    val logging = MutableSharedFlow<String>()

    launch {
        logging.collect {
            log.warn(it)
        }
    }

    routing {
        get(ApiRoutes.Default.route) {
            call.respondText { "Connected" }
        }

        get(ApiRoutes.Products.route) {
            call.respond(products)
        }

        webSocket(ApiRoutes.ClientWebSocket.route) {
            val acceptedOrdersFromDelivery = launch {
                acceptedOrder.collect {
                    logging.emit("Sending accepted order from delivery app: $it")
                    sendSerialized(it)
                }
            }

            runCatching {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val order = receiveDeserialized<Order>()
                        mainOrders.update {
                            val newOrders = mainOrders.value.toMutableList()
                            newOrders.add(order)
                            newOrders
                        }
                        logging.emit("New pending order created: $order")
                    }
                }
            }.onFailure { exception ->
                println("WebSocket exception: ${exception.localizedMessage}")
            }.also {
                acceptedOrdersFromDelivery.cancel()
            }
            //setClientWebSocket(mainOrders, acceptedOrder, logging)
        }

        webSocket(ApiRoutes.DeliverWebSocket.route) {
            val pendingOrdersToDelivery = launch {
                mainOrders.collect { orders ->
                    orders.forEach { order ->
                        sendSerialized(order)
                        logging.emit("Sending pending orders to delivery app: $order")
                    }
                }
            }

            runCatching {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val order = receiveDeserialized<Order>()
                        logging.emit("Accepted order ${order.address}, removing from list")
                        mainOrders.update {
                            mainOrders.value.toMutableList().filterNot { it == order }
                        }
                        acceptedOrder.emit(order)
                    }
                }
            }.onFailure { exception ->
                println("WebSocket exception: ${exception.localizedMessage}")
            }.also {
                pendingOrdersToDelivery.cancel()
            }
            //setDeliverWebSocket(mainOrders, acceptedOrder, logging)
        }
    }
}
