package br.gohan.pizzacmp.presenter.actions

import data.model.PizzaSelected

sealed class CheckoutAction {
    data class Remove(val selectionUi: PizzaSelected) : CheckoutAction()
    data class Toppings(val selectionUi: PizzaSelected) : CheckoutAction()
    data class Order(val selectionUi: List<PizzaSelected>) : CheckoutAction()
}