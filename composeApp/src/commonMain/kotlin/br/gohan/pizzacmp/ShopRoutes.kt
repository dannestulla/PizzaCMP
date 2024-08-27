package br.gohan.pizzacmp


enum class PizzaRoutes {
    Products,
    Product,
    Checkout,
    Map,
    Chat
}

fun String.getRoute(): PizzaRoutes {
    val route = this.split("/").first()
    return when (route) {
        "Products" -> PizzaRoutes.Products
        "Product" -> PizzaRoutes.Product
        "Map" -> PizzaRoutes.Map
        "Checkout" -> PizzaRoutes.Checkout
        "Chat" -> PizzaRoutes.Chat
        else -> throw IllegalArgumentException("Invalid route: $this")
    }
}