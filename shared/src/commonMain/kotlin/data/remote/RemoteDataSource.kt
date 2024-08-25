package data.remote

import data.model.Categories
import data.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSource(
    private val httpClient: HttpClient,
) {
    private val baseUrl = "https://api.escuelajs.co/api/v1"


    suspend fun getProducts(): List<Product> {
        return httpClient.get("$baseUrl/products").body()
    }

    suspend fun getCategories(): List<Categories> {
        return httpClient.get("$baseUrl/categories").body<List<Categories>>().subList(0, 5)
        // Sublist to avoid weird categories from API
    }
}