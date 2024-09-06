package br.gohan.deliveryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeliveryViewModel : ViewModel() {
    private val repository = DeliveryRepository()

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders = _orders.asStateFlow()

    init {
        fetchOrders()
        repository.setHttpClient()
        observeNewOrders()
    }

    private fun fetchOrders() {

    }

    fun acceptOrder(order: Order) {
        viewModelScope.launch {
            repository.acceptOrder(order)
        }
    }

    private fun observeNewOrders() {
        viewModelScope.launch {
            repository.observeOrders { order ->
                _orders.update {
                    val newOrders = it.toMutableList()
                    newOrders.remove(order)
                    newOrders
                }
            }
        }
    }
}