package br.gohan.pizzacmp.database


import androidx.datastore.preferences.core.stringPreferencesKey
import data.model.PizzaProduct


val PRODUCT_KEY = stringPreferencesKey("product")


interface DataStoreManager {
    suspend fun cacheProduct(productUI: PizzaProduct)

    suspend fun retrieveProduct(): PizzaProduct?
}
