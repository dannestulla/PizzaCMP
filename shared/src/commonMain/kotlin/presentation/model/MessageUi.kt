package presentation.model

sealed class MessageUi {
    data class Someone(val message: String, val hour: String) : MessageUi()
    data class Mine(val message: String, val hour: String) : MessageUi()
}

