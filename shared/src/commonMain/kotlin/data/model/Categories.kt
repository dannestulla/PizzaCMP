package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val name: String,
    val image: String
)


