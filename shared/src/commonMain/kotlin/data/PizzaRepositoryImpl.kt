package data

import data.local.LocalDataSource
import data.model.Order
import data.model.PizzaProduct
import data.model.PizzaSelected
import data.remote.RemoteDataSource
import database.Checkout
import kotlinx.coroutines.flow.Flow
import presentation.model.NewMessage


class PizzaRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PizzaRepository {


    override suspend fun getProducts(): List<PizzaProduct> {
        return remoteDataSource.getProducts()
    }

    override suspend fun getCheckoutItems(): Flow<List<Checkout>> {
        return localDataSource.getItems()
    }

    override suspend fun saveCheckoutItem(item: PizzaSelected) {
        localDataSource.saveItem(item)
    }

    override suspend fun deleteCheckoutItem(item: PizzaSelected) {
        localDataSource.deleteItem(item.name)
    }

    override suspend fun getMessages(): Flow<NewMessage> {
        return remoteDataSource.getMessages()
    }

    override suspend fun sendOrder(selectionUi: Order) {
        remoteDataSource.sendOrder(selectionUi)
    }

}