package br.gohan.deliveryapp.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.deliveryapp.data.DeliveryRepository
import data.model.Driver
import data.model.Order
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val repository: DeliveryRepository
) : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(listOf())
    val orders = _orders.asStateFlow()

    private val _acceptOrder = MutableSharedFlow<Order>()
    val acceptOrder = _acceptOrder.asSharedFlow()

    init {
        observeNewOrders()
    }

    private fun observeNewOrders() {
        viewModelScope.launch {
            repository.observeOrders(_orders, _acceptOrder)
        }
    }

    fun acceptOrder(order: Order) {
        viewModelScope.launch {
            val orderWithDeliveryData = order.copy(
                driverResponsible = Driver(name = "John Doe", phoneNumber = "123-456-7890"),
                estimatedTime = "20 min",
                deliveryFee = "R$ 10,00"
            )
            _acceptOrder.emit(orderWithDeliveryData)
        }
    }
}