package br.gohan.pizzacmp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import presentation.model.ExampleUi

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "shop_pref")


class AndroidDataStoreManager(private val applicationContext: Context) : DataStoreManager {
    override suspend fun cacheProduct(productUI: ExampleUi) {
        val jsonString = Json.encodeToString(ExampleUi.serializer(), productUI)
        (applicationContext).dataStore.edit {
            it[PRODUCT_KEY] = jsonString
        }
    }

    override suspend fun retrieveProduct(): ExampleUi? {
        return (applicationContext).dataStore.data
            .map { preferences ->
                val jsonString = preferences[PRODUCT_KEY]
                jsonString?.let {
                    Json.decodeFromString(ExampleUi.serializer(), it)
                }
            }.first()
    }
}