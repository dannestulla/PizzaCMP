package br.gohan.pizzacmp.presenter.models

data class Selection(
    val title: String,
    val image: String? = null,
    val toppings: String,
    val price: String
)