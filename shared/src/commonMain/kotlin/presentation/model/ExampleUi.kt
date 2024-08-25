package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class ExampleUi(
    val title: String,
    val oldPrice: String,
    val newPrice: String,
    val discount: String,
    val description: String,
    val images: List<String>,
    val category: String,
    val isFavorite: Boolean = false,
    val sizeSelected: String? = null
)

