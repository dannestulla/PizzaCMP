package presentation.viewmodels

import androidx.lifecycle.ViewModel
import data.PizzaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.model.MapState

class DeliverViewModel(
    private val repository: PizzaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MapState())
    val state = _state.asStateFlow()

}