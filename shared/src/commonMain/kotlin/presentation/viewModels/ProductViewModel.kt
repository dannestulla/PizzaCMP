package presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PizzaRepository
import data.model.PizzaSelected
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: PizzaRepository
) : ViewModel() {

    fun addToCheckout(productUi: PizzaSelected) {
        viewModelScope.launch { repository.saveCheckoutItem(productUi) }
    }
}