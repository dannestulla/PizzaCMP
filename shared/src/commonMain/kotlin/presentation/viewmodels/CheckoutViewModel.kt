package presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PizzaRepository
import data.model.PizzaSelected
import data.model.Position
import domain.toOrder
import domain.toProductUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.model.CheckoutState

class CheckoutViewModel(
    private val repository: PizzaRepository
) : ViewModel() {
    private val mockedGPSPosition = Position("-23.533773", "-46.625290")

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

    fun deleteCheckoutItem(item: PizzaSelected) {
        viewModelScope.launch {
            repository.deleteCheckoutItem(item)
        }
    }

    fun updateToppings(newProduct: PizzaSelected) {
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

    fun sendOrder(selectionUi: List<PizzaSelected>) {
        viewModelScope.launch {
            repository.sendOrder(selectionUi.toOrder(mockedGPSPosition)) {

            }
        }
    }
}