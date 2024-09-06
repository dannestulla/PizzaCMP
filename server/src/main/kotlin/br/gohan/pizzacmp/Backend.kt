package br.gohan.pizzacmp

import data.SERVER_IP
import data.SERVER_PORT
import data.model.Order
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.sse.sse
import io.ktor.server.websocket.webSocket
import io.ktor.sse.ServerSentEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

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

    routing {
        get("/") {
            call.respondText { "Connected" }
        }

        get("/products") {
            call.respond(products)
        }

        get("/driver") {
            call.respond(driver)
        }

        get("/directions") {
            call.respond(mapDirections)
        }

        post("/new-order") {
            val order = call.receive<Order>()
            mainOrders.update {
                val newOrders = mainOrders.value.toMutableList()
                newOrders.add(order)
                newOrders
            }
        }

        webSocket("/accept-order") {
            val acceptedOrder = call.receive<Order>()
            mainOrders.update {
                val newOrders = mainOrders.value.toMutableList()
                newOrders.remove(acceptedOrder)
                newOrders
            }

        }

        sse("/notify-deliver") {
            mainOrders.collect { orders ->
                orders.forEach { order ->
                    val orderJson = Json.encodeToString(Order.serializer(), order)
                    mainOrders.update {
                        val newOrders = it.toMutableList()
                        newOrders.remove(order)
                        newOrders
                    }
                    send(ServerSentEvent(orderJson, "new-order"))
                }
            }
        }
    }
}


