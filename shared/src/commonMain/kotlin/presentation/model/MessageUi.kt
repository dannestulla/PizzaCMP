package presentation.model

sealed class Message {
    data class Someone(val message: String, val hour: String) : Message()
    data class Mine(val message: String, val hour: String) : Message()
}

