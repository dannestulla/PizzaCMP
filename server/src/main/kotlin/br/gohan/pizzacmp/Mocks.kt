package br.gohan.pizzacmp

import data.model.PizzaProduct
import kotlinx.coroutines.flow.flowOf
import presentation.model.DriverState
import presentation.model.MapDirectionsState
import presentation.model.NewMessage

val products = listOf(
    PizzaProduct(
        image = "",
        name = "Pepperoni",
        description = "Pepperoni, cheese, olives, bell peppers, and red sauce.",
        toppings = listOf("onion", "tomato"),
        prices = listOf(12.32, 13.50, 14.00)
    ),
    PizzaProduct(
        image = "",
        name = "Margherita",
        description = "Tomato, mozzarella, basil, and olive oil.",
        toppings = listOf("basil", "tomato"),
        prices = listOf(10.50, 11.00, 11.50)
    ),
    PizzaProduct(
        image = "",
        name = "BBQ Chicken",
        description = "Chicken, BBQ sauce, red onions, and cilantro.",
        toppings = listOf("chicken", "onion"),
        prices = listOf(13.75, 14.25, 14.75)
    ),
    PizzaProduct(
        image = "",
        name = "Hawaiian",
        description = "Ham, pineapple, cheese, and tomato sauce.",
        toppings = listOf("ham", "pineapple"),
        prices = listOf(11.20, 11.70, 12.20)
    ),
    PizzaProduct(
        image = "",
        name = "Veggie",
        description = "Bell peppers, onions, mushrooms, olives, and tomato sauce.",
        toppings = listOf("bell peppers", "mushrooms"),
        prices = listOf(11.00, 11.50, 12.00)
    )
)

val messages = flowOf(
    NewMessage.Someone("Hello, I am delivering your order", "10:10"),
    NewMessage.Mine("Great, thank you! How long will it take?", "10:11"),
    NewMessage.Someone("It should be there in about 20 minutes.", "10:12"),
    NewMessage.Mine("Perfect, see you soon!", "10:13"),
    NewMessage.Someone("Your order has arrived. Enjoy your meal!", "10:30"),
    NewMessage.Mine("Thank you! The pizzas look delicious.", "10:31")
)

val driver = DriverState(
    "Joao Miguel",
    "https://media.istockphoto.com/id/480750456/pt/foto/alegre-homem-de-entrega.jpg?s=1024x1024&w=is&k=20&c=uNnorOCSxcJuPIvmY0Mk1K3cCC2dl4ZBrdh0HkJro98="
)

val mapDirections = MapDirectionsState(
    listOf("123 Main St", "456 Elm St", "789 Oak St"),
    "10 min "
)

