package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class DriverState(
    val name: String,
    val image: String
)
