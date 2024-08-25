package br.gohan.pizzacmp.examples.screens/*
package br.gohan.pizzacmp.examples.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.examples.components.ProductAction
import br.gohan.pizzacmp.examples.components.ProductComponent
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import presentation.FavoritesViewModel
import presentation.model.ExampleUi

@Composable
fun FavoritesScreen(
    shopParameters: ShopParameters,
    favoritesViewModel: FavoritesViewModel = koinInject()
) = with(shopParameters) {
    val state by favoritesViewModel.state.collectAsState()

    FavoritesScreenStateless(state.products, paddingValues) { action ->
        when (action) {
            is ProductAction.Navigate -> {
                shopParameters.coroutine.launch {
                    dataStoreManager.cacheProduct(action.product)
                }
                navController.navigate(AppRoutes.PRODUCT.name)
            }
            is ProductAction.RemoveFavorite -> {
                favoritesViewModel.removeFavorite(action.product)
                coroutine.launch {
                    snackbar.showSnackbar("Removed from favorites")
                }
            }
            is ProductAction.AddToCart -> {}
            is ProductAction.Favorite -> {}
        }
    }

}

@Composable
fun FavoritesScreenStateless(
    product: List<ExampleUi>?,
    paddingValues: PaddingValues,
    action: (ProductAction) -> Unit
) {
    if (product == null) {
        LoadingScreen()
        return
    }

    Column(
        verticalArrangement = if (product.isEmpty()) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = if (product.isEmpty()) Alignment.CenterHorizontally else Alignment.Start,
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
    ) {
        if (product.isEmpty()) {
            Text(
                "No favorites yet", fontSize = Dimens.fontSmall,
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                ),
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(product.size) { index ->
                    ProductComponent(product[index], true, action)
                }
            }
        }
    }
}
*/
