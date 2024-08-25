package br.gohan.pizzacmp


enum class PizzaRoutes {
    Products,
    Checkout,
    Delivery
}

fun String.getRoute(): PizzaRoutes {
    val route = this.split("/").first()
    return when (route) {
        "Products" -> PizzaRoutes.Products
        "Delivery" -> PizzaRoutes.Delivery
        "Checkout" -> PizzaRoutes.Checkout
        else -> throw IllegalArgumentException("Invalid route: $this")
    }
}