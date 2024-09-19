package br.gohan.deliveryapp

import api.EMULATOR_IP
import api.SERVER_PORT
import br.gohan.deliveryapp.data.DeliveryRepository
import br.gohan.deliveryapp.presenter.DeliveryViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration? = null,
) = startKoin {
    if (appDeclaration != null) {
        appDeclaration()
    }
    modules(
        app
    )
}

val app = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            defaultRequest {
                url(
                    host = EMULATOR_IP,
                    port = SERVER_PORT,
                )
            }
        }
    }

    single { DeliveryRepository(get()) }

    viewModel { DeliveryViewModel(get()) }
}