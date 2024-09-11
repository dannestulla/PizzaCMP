package data.model

import kotlinx.serialization.Serializable

@Serializable
enum class PizzaSize {
    NotSelected,
    Small,
    Medium,
    Large,
}
