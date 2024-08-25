package br.gohan.pizzacmp


import androidx.datastore.preferences.core.stringPreferencesKey
import presentation.model.ExampleUi


val PRODUCT_KEY = stringPreferencesKey("product")


interface DataStoreManager {
    suspend fun cacheProduct(productUI: ExampleUi)

    suspend fun retrieveProduct(): ExampleUi?
}
