package br.gohan.pizzacmp

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.LoggerFactory

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val log = LoggerFactory.getLogger(Application::class.java)

    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            log.info("Connected")
            call.respondText { "Connected" }
        }

        get("/products") {
            log.info(products.toString())
            call.respond(products)
        }

        get("/driver") {
            log.info(driver.toString())
            call.respond(driver)
        }

        get("/directions") {
            log.info(mapDirections.toString())
            call.respond(mapDirections)
        }
    }
}

