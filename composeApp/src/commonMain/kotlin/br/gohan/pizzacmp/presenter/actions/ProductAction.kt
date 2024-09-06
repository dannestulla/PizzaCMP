package br.gohan.pizzacmp.presenter.actions

import data.model.PizzaProduct


sealed class ProductAction {
    data class Add(val product: PizzaProduct) : ProductAction()
    data class SeeMore(val product: PizzaProduct) : ProductAction()
}