package presentation.model


data class TopBarState(
    var title: String,
    val isSearchActive: Boolean = false,
    val noSearch: Boolean = false
)
