package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val latitude: String,
    val longitude: String
)
