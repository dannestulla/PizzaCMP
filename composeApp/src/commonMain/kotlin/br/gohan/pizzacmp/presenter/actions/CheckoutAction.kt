package br.gohan.pizzacmp.presenter.actions

import presentation.model.PizzaProductUi

sealed class CheckoutAction {
    data class Remove(val selectionUi: PizzaProductUi) : CheckoutAction()
    data class Toppings(val selectionUi: PizzaProductUi) : CheckoutAction()
    data class Order(val selectionUi: List<PizzaProductUi>) : CheckoutAction()
}