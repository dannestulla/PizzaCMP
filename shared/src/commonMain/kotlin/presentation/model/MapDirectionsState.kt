package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class MapDirectionsState(
    val routes: List<String>,
    val estimatedTime: String
)
