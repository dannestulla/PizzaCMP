package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PizzaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.model.MapState

class DeliverViewModel(
    private val repository: PizzaRepository
) : ViewModel() {

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