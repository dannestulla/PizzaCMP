package br.gohan.pizzacmp.presenter.actions

import presentation.model.PizzaProductUi


sealed class ProductAction {
    data class Add(val product: PizzaProductUi) : ProductAction()
    data class SeeMore(val product: PizzaProductUi) : ProductAction()
}