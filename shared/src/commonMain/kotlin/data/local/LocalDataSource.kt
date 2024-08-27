package data.local

import mocks.products
import presentation.model.PizzaProductUi

class LocalDataSource(
    //database: ShopSampleDatabase,
) {
    //private val favoritesTable = database.favoritesQueries

    fun getProduct(): PizzaProductUi {
        return products.first()
    }

    fun getCheckoutItems(): List<PizzaProductUi> {
        return listOf(products[0], products[1])
    }
    /*

        fun getFavoriteByTitle(title: String): Favorites? {
            return favoritesTable.getFavoriteTitle(title).executeAsList().firstOrNull()
        }

        fun saveFavorite(product: ProductUI, discount: Double) {
            favoritesTable.saveFavorite(
                title = product.title,
                newPrice = product.newPrice,
                oldPrice = product.oldPrice,
                description = product.description,
                image = product.images.first(),
                timestamp = Clock.System.now().toEpochMilliseconds(),
                category = product.category,
                discount = discount.toString(),
            )
        }

        fun removeFavorite(product: ProductUI) {
            val favorite = favoritesTable.getFavoriteTitle(product.title).executeAsList()
            favorite.forEach {
                favoritesTable.removeFavoriteById(it.id)
            }
        }

        fun addToCheckout(product: ProductUI) {
            checkoutTable.addToCheckout(
                title = product.title,
                price = product.newPrice,
                oldPrice = product.oldPrice,
                description = product.description,
                image = product.images.firstOrNull() ?: "",
                category = product.category,
                timestamp = Clock.System.now().toEpochMilliseconds(),
                sizeSelected = product.sizeSelected ?: "40"
            )
        }

        fun getCheckoutItems(): Flow<List<Checkout>> {
            return checkoutTable
                .getCheckoutItems()
                .asFlow()
                .mapToList(Dispatchers.IO)
        }

        fun removeFromCheckout(checkoutItem: CheckoutUI) {
            checkoutTable.getCartItemById(checkoutItem.title).executeAsList().let { product ->
                product.forEach {
                    checkoutTable.removeFromCheckout(it.id)
                }
            }
        }*/
}