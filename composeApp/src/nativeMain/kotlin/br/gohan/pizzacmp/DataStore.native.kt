package br.gohan.pizzacmp

import kotlinx.serialization.json.Json
import presentation.model.ExampleUi


class NativeDataStoreManager : DataStoreManager {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun cacheProduct(productUI: ExampleUi) {
        val jsonString = Json.encodeToString(ExampleUi.serializer(), productUI)
        userDefaults.setObject(jsonString, forKey = PRODUCT_KEY)
    }

    override suspend fun retrieveProduct(): ExampleUi? {
        val jsonString = userDefaults.stringForKey(PRODUCT_KEY)
        return jsonString?.let { json ->
            Json.decodeFromString(ExampleUi.serializer(), json)
        }
    }

}