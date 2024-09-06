package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val name: String,
    val phoneNumber: String
)
