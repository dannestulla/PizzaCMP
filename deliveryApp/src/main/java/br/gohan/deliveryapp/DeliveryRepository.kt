package br.gohan.deliveryapp

import data.CLIENT_IP
import data.model.Order
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.sse.SSE
import io.ktor.client.plugins.sse.sse
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class DeliveryRepository {
    private lateinit var client: HttpClient

    fun setHttpClient() {
        client = HttpClient(CIO) {
            install(SSE)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    suspend fun observeOrders(newOrder: (Order) -> Unit) {
        client.sse(
            host = CLIENT_IP,
            port = 8080,
            path = "/orders"
        ) {
            while (true) {
                incoming.collect { event ->
                    println("Event from server: ${event.data}")
                    if (event.data == null) return@collect
                    val order = Json.decodeFromString(Order.serializer(), event.data!!)
                    newOrder.invoke(order)
                }
            }
        }
    }

    suspend fun acceptOrder(order: Order) {
        client.post("accept-order") {
            contentType(ContentType.Application.Json)
            setBody {
                order
            }
        }
    }
}