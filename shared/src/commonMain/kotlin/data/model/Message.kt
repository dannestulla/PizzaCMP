package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val text: String,
    val date: String
)
