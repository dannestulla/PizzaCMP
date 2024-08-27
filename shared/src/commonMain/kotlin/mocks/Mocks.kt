package mocks

import presentation.model.Driver
import presentation.model.MapDirections
import presentation.model.Message
import presentation.model.PizzaProductUi

val products = listOf(
    PizzaProductUi(
        "",
        "Pepperoni", "Pepperoni, cheese, olives, bell peppers, and red sauce.",
        12.32,
        mapOf("onion" to 1, "tomato" to 2)
    ),
    PizzaProductUi(
        "",
        "Margherita", "Tomato, mozzarella, basil, and olive oil.",
        10.50,
        mapOf("basil" to 1, "tomato" to 3)
    ),
    PizzaProductUi(
        "",
        "BBQ Chicken", "Chicken, BBQ sauce, red onions, and cilantro.",
        13.75,
        mapOf("chicken" to 2, "onion" to 1)
    ),
    PizzaProductUi(
        "",
        "Hawaiian", "Ham, pineapple, cheese, and tomato sauce.",
        11.20,
        mapOf("ham" to 2, "pineapple" to 3)
    ),
    PizzaProductUi(
        "",
        "Veggie", "Bell peppers, onions, mushrooms, olives, and tomato sauce.",
        11.00,
        mapOf("bell peppers" to 2, "mushrooms" to 3)
    )
)

val messages = listOf(
    Message.Someone("Hello, I am delivering your order", "10:10"),
    Message.Mine("Great, thank you! How long will it take?", "10:11"),
    Message.Someone("It should be there in about 20 minutes.", "10:12"),
    Message.Mine("Perfect, see you soon!", "10:13"),
    Message.Someone("Your order has arrived. Enjoy your meal!", "10:30"),
    Message.Mine("Thank you! The pizzas look delicious.", "10:31")
)

val driver = Driver(
    "Joao Miguel",
    "123456789"
)

val mapDirections = MapDirections(
    listOf("123 Main St", "456 Elm St", "789 Oak St"),
    "10 min "
)

