package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.PizzaProductUi

class ProductViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    fun addToCheckout(productUi: PizzaProductUi) {
        viewModelScope.launch { repository.saveCheckoutItem(productUi) }
    }
}