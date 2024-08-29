package data

import data.local.LocalDataSource
import data.remote.RemoteDataSource
import database.Checkout
import kotlinx.coroutines.flow.Flow
import presentation.model.Driver
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

    override suspend fun getCheckoutItems(): Flow<List<Checkout>> {
        return localDataSource.getItems()
    }

    override suspend fun saveCheckoutItem(item: PizzaProductUi) {
        localDataSource.saveItem(item)
    }

    override suspend fun deleteCheckoutItem(item: PizzaProductUi) {
        localDataSource.deleteItem(item.name)
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

    override fun sendOrder(selectionUi: List<PizzaProductUi>) {
        remoteDataSource.sendOrder(selectionUi)
    }

}