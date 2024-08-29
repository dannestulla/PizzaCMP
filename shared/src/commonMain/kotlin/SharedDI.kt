import br.gohan.pizzacmp.database.PizzaDatabase
import data.PizzaRepository
import data.PizzaRepositoryImpl
import data.database
import data.local.LocalDataSource
import data.remote.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import presentation.ChatViewModel
import presentation.CheckoutViewModel
import presentation.DeliverViewModel
import presentation.ProductViewModel
import presentation.ProductsViewModel

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        api,
        database,
        core
    )
}

val api = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}

val core = module {
    factory { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    factory { CheckoutViewModel(get(), get()) }
    factory { DeliverViewModel(get(), get()) }
    single { ChatViewModel(get(), get()) }
    factory { ProductsViewModel(get(), get()) }
    factory { ProductViewModel(get(), get()) }

    single<PizzaRepository> { PizzaRepositoryImpl(get(), get()) }

    factory { PizzaRepositoryImpl(get(), get()) }
    factory { RemoteDataSource(get()) }
    factory { LocalDataSource(get()) }

    single {
        PizzaDatabase(
            get(),
        )
    }
}
