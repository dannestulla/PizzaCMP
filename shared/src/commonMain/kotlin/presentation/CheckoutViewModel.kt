package presentation

import data.PizzaRepository
import domain.mappers.toProductUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.CheckoutState
import presentation.model.PizzaProductUi

class CheckoutViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    var state = MutableStateFlow(CheckoutState())
        private set

    init {
        getCheckoutItems()
    }

    private fun getCheckoutItems() {
        viewModelScope.launch {
            repository.getCheckoutItems().collect { items ->
                state.update {
                    CheckoutState(items.toProductUi())
                }
            }
        }
    }

    fun deleteCheckoutItem(item: PizzaProductUi) {
        viewModelScope.launch {
            repository.deleteCheckoutItem(item)
        }
    }

    fun updateToppings(newProduct: PizzaProductUi) {
        val updatedList = state.value.products?.map { oldProduct ->
            if (oldProduct.name == newProduct.name) {
                newProduct
            } else {
                oldProduct
            }
        }
        return state.update {
            CheckoutState(updatedList)
        }
    }

    fun sendOrder(selectionUi: List<PizzaProductUi>) {
        viewModelScope.launch {
            repository.sendOrder(selectionUi)
        }
    }
}