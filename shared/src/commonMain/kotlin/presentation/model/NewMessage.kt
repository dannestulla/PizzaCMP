package presentation.model

import data.model.Order
import kotlinx.serialization.Serializable

sealed class NewMessage {
    @Serializable
    data class Someone(val message: String, val hour: String) : NewMessage()

    @Serializable
    data class Mine(val message: String, val hour: String) : NewMessage()

    @Serializable
    data class AcceptOrder(val order: Order) : NewMessage()
}

