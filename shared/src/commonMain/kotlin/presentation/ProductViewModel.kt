package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.PizzaProductUi
import presentation.model.ProductState
import presentation.model.ProductsState

class ProductViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {


}