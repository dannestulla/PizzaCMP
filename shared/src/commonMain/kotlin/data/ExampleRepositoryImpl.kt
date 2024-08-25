package data

import data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import presentation.model.ExampleUi


class ExampleRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource
) : ExampleRepository {
    override fun getExample(): Flow<List<ExampleUi>> {
        TODO("Not yet implemented")
    }

    override fun saveExample(product: ExampleUi, discount: Double) {
        //return localDataSource.saveExample(product, discount)
    }

    override fun removeExample(product: ExampleUi) {
        //localDataSource.removeExample(product)
    }
}