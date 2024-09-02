package br.gohan.pizzacmp

import data.PizzaRepository
import data.PizzaRepositoryImpl
import data.local.LocalDataSource
import data.remote.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import presentation.ChatViewModel
import presentation.CheckoutViewModel
import presentation.DeliverViewModel
import presentation.ProductViewModel
import presentation.ProductsViewModel

fun initKoin(
    appDeclaration: KoinAppDeclaration? = null,
) = startKoin {
    if (appDeclaration != null) {
        appDeclaration()
    }
    modules(
        api,
        core,
        database
    )
}

expect val database: Module


val api = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            defaultRequest {
                url("http://10.0.2.2:8080")
            }
        }
    }
}

val core = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            defaultRequest {
                url("http://10.0.2.2:8080")
            }
        }
    }
    viewModel { CheckoutViewModel(get()) }
    viewModel { DeliverViewModel(get()) }
    viewModel { ChatViewModel(get()) }
    viewModel { ProductsViewModel(get()) }
    viewModel { ProductViewModel(get()) }

    single<PizzaRepository> { PizzaRepositoryImpl(get(), get()) }

    factory { PizzaRepositoryImpl(get(), get()) }
    factory { RemoteDataSource(get()) }
    factory { LocalDataSource() }
}

