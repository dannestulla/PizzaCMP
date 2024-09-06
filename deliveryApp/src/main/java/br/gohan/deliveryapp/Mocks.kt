package br.gohan.deliveryapp

import data.model.Driver
import data.model.Order
import data.model.Position

val orders = listOf(
    Order(
        pizzas = listOf("Margherita", "Pepperoni"),
        numberOfProducts = 2,
        address = "123 Pizza Street, Flavor Town",
        addressPosition = Position("40.712776", "-74.005974"),
        driverResponsible = Driver(name = "John Doe", phoneNumber = "123-456-7890"),
        estimatedTime = "20 min",
        deliveryFee = "R$ 10,00"
    ),
    Order(
        pizzas = listOf("Hawaiian", "Veggie", "BBQ Chicken"),
        numberOfProducts = 3,
        address = "456 Tasty Avenue, Delicious City",
        addressPosition = Position("34.052235", "-118.243683"),
        driverResponsible = Driver(name = "Jane Smith", phoneNumber = "987-654-3210"),
        estimatedTime = "30 min",
        deliveryFee = "R$ 20,00"
    ),
    Order(
        pizzas = listOf("Four Cheese", "Meat Lovers"),
        numberOfProducts = 2,
        address = "789 Yummy Road, Savory Town",
        addressPosition = Position("51.507351", "-0.127758"),
        estimatedTime = "50 min",
        deliveryFee = "R$ 25,00"
    )
)
