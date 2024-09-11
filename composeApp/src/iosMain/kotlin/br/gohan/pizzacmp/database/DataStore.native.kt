package br.gohan.pizzacmp.database

import data.model.PizzaProduct
import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults


class NativeDataStoreManager : DataStoreManager {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun cacheProduct(productUI: PizzaProduct) {
        val jsonString = Json.encodeToString(PizzaProduct.serializer(), productUI)
        userDefaults.setObject(jsonString, forKey = PRODUCT_KEY.toString())
    }

    override suspend fun retrieveProduct(): PizzaProduct? {
        val jsonString = userDefaults.stringForKey(PRODUCT_KEY.toString())
        return jsonString?.let { json ->
            Json.decodeFromString(PizzaProduct.serializer(), json)
        }
    }
}