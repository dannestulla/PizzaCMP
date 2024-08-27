package data

import data.local.LocalDataSource
import data.remote.Products
import data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import mocks.products
import presentation.model.Driver
import presentation.model.ExampleUi
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi


class PizzaRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PizzaRepository {
    override suspend fun getProducts(): List<PizzaProductUi> {
        return remoteDataSource.getProducts()
    }

    override suspend fun getProduct(): PizzaProductUi {
        return localDataSource.getProduct()
    }

    override suspend fun getCheckoutItems(): List<PizzaProductUi> {
        return localDataSource.getCheckoutItems()
    }

    override suspend fun getDriver(): Driver {
        return remoteDataSource.getDriver()
    }

    override suspend fun getMapDirections(): MapDirections {
        return remoteDataSource.getMapDirections()
    }

    override suspend fun getMessages(): Flow<Message> {
        return remoteDataSource.getMessages()
    }

}