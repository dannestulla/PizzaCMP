package br.gohan.pizzacmp


enum class PizzaRoutes {
    Products,
    Product,
    Checkout,
    Deliver,
    Chat
}

fun String.getRoute(): PizzaRoutes {
    val route = this.split("/").first()
    return when (route) {
        "Products" -> PizzaRoutes.Products
        "Product" -> PizzaRoutes.Product
        "Deliver" -> PizzaRoutes.Deliver
        "Checkout" -> PizzaRoutes.Checkout
        "Chat" -> PizzaRoutes.Chat
        else -> throw IllegalArgumentException("Invalid route: $this")
    }
}