package br.gohan.pizzacmp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.gohan.pizzacmp.presenter.screens.ChatScreen
import br.gohan.pizzacmp.presenter.screens.CheckoutScreen
import br.gohan.pizzacmp.presenter.screens.MapScreen
import br.gohan.pizzacmp.presenter.screens.ProductScreen
import br.gohan.pizzacmp.presenter.screens.ProductsScreen
import kotlinx.coroutines.CoroutineScope
import presentation.model.PizzaProductUi
import presentation.model.TopBarState

@Composable
fun PizzaNavigation(
    pizzaParameters: PizzaParameters,
) = with(pizzaParameters) {
    NavHost(
        navController = navController,
        startDestination = PizzaRoutes.Products.name
    ) {
        var productSelected: PizzaProductUi? = null
        composable(route = PizzaRoutes.Products.name) {
            ProductsScreen(paddingValues, currentSearch, dataStoreManager) { category ->
                productSelected = category
                navController.navigate(PizzaRoutes.Product.name)
            }
        }
        composable(
            route = PizzaRoutes.Product.name
        ) {
            ProductScreen(paddingValues, productSelected)
        }
        composable(route = PizzaRoutes.Checkout.name) {
            CheckoutScreen(paddingValues)
        }
        composable(route = PizzaRoutes.Map.name) {
            MapScreen()
        }
        composable(route = PizzaRoutes.Chat.name) {
            ChatScreen() {

            }
        }
    }
}

data class PizzaParameters(
    val navController: NavHostController,
    val currentSearch: String?,
    val topBarState: TopBarState,
    val dataStoreManager: DataStoreManager,
    val paddingValues: PaddingValues,
    val snackbar: SnackbarHostState,
    val coroutine: CoroutineScope
)