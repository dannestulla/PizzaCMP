import data.model.Categories
import database.Example

val productsMock = listOf(
    ProductUI(
        "Shoe",
        "R$100,00",
        "R$50,00",
        "20 %",
        "Clothes",
        listOf(),
        "Clothes",
    ),
    ProductUI(
        "Shoe2",
        "100.0",
        "50",
        "20 %",
        "Clothes",
        listOf("img1", "img2"),
        "Clothes",
    ),
    ProductUI(
        "Shoe2",
        "100.0",
        "50",
        "20 %",
        "Clothes",
        listOf(),
        "Clothes",
    ),
)

val favoriteFixture = listOf(
    Example(
        1,
        "Shoe",
        "R$50,00",
        "R$100,00",
        "Clothes",
        "",
        1,
        "Clothes",
        "20 %",
    )
)

val categoriesMock = listOf(
    Categories(
        "first",
        "image"
    ),
    Categories(
        "second",
        "image"
    )
)


