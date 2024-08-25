package data

import kotlinx.coroutines.flow.Flow
import presentation.model.ExampleUi

interface ExampleRepository {

    fun getExample(): Flow<List<ExampleUi>>

    fun saveExample(product: ExampleUi, discount: Double)

    fun removeExample(product: ExampleUi)

  }