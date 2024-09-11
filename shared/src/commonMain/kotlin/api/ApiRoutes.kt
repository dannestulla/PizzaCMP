package api

enum class ApiRoutes(val route: String) {
    Default("/"),
    Products("/products"),
    Driver("/driver"),
    Directions("/directions"),
    NewOrder("/new-order"),
    FetchOrders("/fetch-orders"),
    DeliverWebSocket("/deliver-ws"),
    ClientWebSocket("/client-ws"),
    AwaitingDriver("/awaiting-driver")
}