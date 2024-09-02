package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PizzaRepository
import kotlinx.coroutines.launch
import presentation.model.PizzaProductUi

class ProductViewModel(
    private val repository: PizzaRepository
) : ViewModel() {

    fun addToCheckout(productUi: PizzaProductUi) {
        viewModelScope.launch { repository.saveCheckoutItem(productUi) }
    }
}