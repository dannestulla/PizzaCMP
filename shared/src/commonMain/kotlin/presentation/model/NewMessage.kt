package presentation.model

sealed class NewMessage {
    data class Someone(val message: String, val hour: String) : NewMessage()
    data class Mine(val message: String, val hour: String) : NewMessage()
}

