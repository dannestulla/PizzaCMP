package br.gohan.pizzacmp

import kotlinx.coroutines.flow.flowOf
import presentation.model.Driver
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi
import presentation.model.PizzaSize

val products = listOf(
    PizzaProductUi(
        image = "",
        name = "Pepperoni",
        description = "Pepperoni, cheese, olives, bell peppers, and red sauce.",
        toppings = mapOf("onion" to 1, "tomato" to 2),
        prices = listOf(12.32, 13.50, 14.00),
        sizeSelected = PizzaSize.Medium
    ),
    PizzaProductUi(
        image = "",
        name = "Margherita",
        description = "Tomato, mozzarella, basil, and olive oil.",
        toppings = mapOf("basil" to 1, "tomato" to 3),
        prices = listOf(10.50, 11.00, 11.50),
        sizeSelected = PizzaSize.Medium
    ),
    PizzaProductUi(
        image = "",
        name = "BBQ Chicken",
        description = "Chicken, BBQ sauce, red onions, and cilantro.",
        toppings = mapOf("chicken" to 2, "onion" to 1),
        prices = listOf(13.75, 14.25, 14.75),
        sizeSelected = PizzaSize.Medium
    ),
    PizzaProductUi(
        image = "",
        name = "Hawaiian",
        description = "Ham, pineapple, cheese, and tomato sauce.",
        toppings = mapOf("ham" to 2, "pineapple" to 3),
        prices = listOf(11.20, 11.70, 12.20),
        sizeSelected = PizzaSize.Medium
    ),
    PizzaProductUi(
        image = "",
        name = "Veggie",
        description = "Bell peppers, onions, mushrooms, olives, and tomato sauce.",
        toppings = mapOf("bell peppers" to 2, "mushrooms" to 3),
        prices = listOf(11.00, 11.50, 12.00),
        sizeSelected = PizzaSize.Medium
    )
)


val messages = flowOf(
    Message.Someone("Hello, I am delivering your order", "10:10"),
    Message.Mine("Great, thank you! How long will it take?", "10:11"),
    Message.Someone("It should be there in about 20 minutes.", "10:12"),
    Message.Mine("Perfect, see you soon!", "10:13"),
    Message.Someone("Your order has arrived. Enjoy your meal!", "10:30"),
    Message.Mine("Thank you! The pizzas look delicious.", "10:31")
)

val driver = Driver(
    "Joao Miguel",
    "https://media.istockphoto.com/id/480750456/pt/foto/alegre-homem-de-entrega.jpg?s=1024x1024&w=is&k=20&c=uNnorOCSxcJuPIvmY0Mk1K3cCC2dl4ZBrdh0HkJro98="
)

val mapDirections = MapDirections(
    listOf("123 Main St", "456 Elm St", "789 Oak St"),
    "10 min "
)

