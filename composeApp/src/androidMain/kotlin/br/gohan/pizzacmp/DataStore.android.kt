package br.gohan.pizzacmp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import data.model.PizzaProduct
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pizza")


class AndroidDataStoreManager(private val applicationContext: Context) : DataStoreManager {
    override suspend fun cacheProduct(productUI: PizzaProduct) {
        val jsonString = Json.encodeToString(PizzaProduct.serializer(), productUI)
        (applicationContext).dataStore.edit {
            it[PRODUCT_KEY] = jsonString
        }
    }

    override suspend fun retrieveProduct(): PizzaProduct? {
        return (applicationContext).dataStore.data
            .map { preferences ->
                val jsonString = preferences[PRODUCT_KEY]
                jsonString?.let {
                    Json.decodeFromString(PizzaProduct.serializer(), it)
                }
            }.first()
    }
}