package data.model

import kotlinx.serialization.Serializable

@Serializable
data class MapDirections(
    val routes: List<String>,
    val estimatedTime: String
)
