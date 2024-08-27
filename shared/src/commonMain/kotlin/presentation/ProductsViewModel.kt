package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.ProductsState

class ProductsViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope,
) : KoinComponent {

    private val _state = MutableStateFlow(ProductsState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _state.update {
                ProductsState(repository.getProducts())
            }
        }
    }
}