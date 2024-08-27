package br.gohan.pizzacmp


import androidx.datastore.preferences.core.stringPreferencesKey
import presentation.model.PizzaProductUi


val PRODUCT_KEY = stringPreferencesKey("product")


interface DataStoreManager {
    suspend fun cacheProduct(productUI: PizzaProductUi)

    suspend fun retrieveProduct(): PizzaProductUi?
}
