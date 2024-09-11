package br.gohan.pizzacmp

import api.ApiRoutes
import api.SERVER_IP
import api.SERVER_PORT
import data.model.Order
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    routing {
        get(ApiRoutes.Default.route) {
            call.respondText { "Connected" }
        }

        get(ApiRoutes.Products.route) {
            call.respond(products)
        }

        webSocket(ApiRoutes.ClientWebSocket.route) {
            setClientWebSocket(mainOrders)
        }

        webSocket(ApiRoutes.DeliverWebSocket.route) {
            setDeliverWebSocket(mainOrders, acceptedOrder)
        }
    }
}


