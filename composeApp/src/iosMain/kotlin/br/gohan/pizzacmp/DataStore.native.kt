package br.gohan.pizzacmp

import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults
import presentation.model.PizzaProductUi


class NativeDataStoreManager : DataStoreManager {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun cacheProduct(productUI: PizzaProductUi) {
        val jsonString = Json.encodeToString(PizzaProductUi.serializer(), productUI)
        userDefaults.setObject(jsonString, forKey = PRODUCT_KEY.toString())
    }

    override suspend fun retrieveProduct(): PizzaProductUi? {
        val jsonString = userDefaults.stringForKey(PRODUCT_KEY.toString())
        return jsonString?.let { json ->
            Json.decodeFromString(PizzaProductUi.serializer(), json)
        }
    }
}