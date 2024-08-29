package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.MapState

class DeliverViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    private val _state = MutableStateFlow(MapState())
    val state = _state.asStateFlow()

    init {
        getDriver()
        getMapDirections()
    }

    private fun getDriver() {
        viewModelScope.launch {
            _state.update { mapState ->
                mapState.copy(driver = repository.getDriver())
            }
        }
    }

    private fun getMapDirections() {
        viewModelScope.launch {
            _state.update { mapState ->
                mapState.copy(mapDirections = repository.getMapDirections())
            }
        }
    }
}