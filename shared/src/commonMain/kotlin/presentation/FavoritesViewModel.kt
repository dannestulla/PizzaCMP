package presentation

import data.ExampleRepository
import domain.mappers.toProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.ExampleState
import presentation.model.ExampleUi


class FavoritesViewModel(
    private val repository: ExampleRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    private val _state = MutableStateFlow(ExampleState())
    val state = _state.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            repository.getExample().collect { favorites ->
                _state.update {
                    ExampleState(favorites.toProduct())
                }
            }
        }
    }

    fun removeFavorite(product: ExampleUi) {
        viewModelScope.launch {
            repository.removeExample(product)
        }
    }
}

