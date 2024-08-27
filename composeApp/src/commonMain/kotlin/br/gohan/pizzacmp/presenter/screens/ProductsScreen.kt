package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.gohan.pizzacmp.DataStoreManager
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.examples.screens.LoadingScreen
import br.gohan.pizzacmp.presenter.components.CardProduct
import presentation.model.PizzaProductUi
import org.koin.compose.koinInject
import presentation.ProductsViewModel

@Composable
fun ProductsScreen(
    paddingValues: PaddingValues,
    currentSearch: String?,
    dataStore: DataStoreManager,
    viewModel: ProductsViewModel = koinInject(),
    action: (PizzaProductUi) -> Unit
) {
    val products by viewModel.state.collectAsState()
    if (products.products == null) {
        LoadingScreen()
        return
    } else {
        ProductsScreenStateless(
            paddingValues,
            products.products,
            currentSearch,
            action
        )
    }
}


@Composable
fun ProductsScreenStateless(
    paddingValues: PaddingValues,
    products: List<PizzaProductUi>? = null,
    currentSearch: String?,
    action: (PizzaProductUi) -> Unit
) {
    if (products == null) {
        LoadingScreen()
        return
    }
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(Dimens.paddingInsideItemsSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.paddingInsideItemsSmall),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(paddingValues),
        columns = GridCells.Fixed(2)
    ) {
        val filteredProducts = currentSearch?.let {
            products.filter {
                it.name.contains(currentSearch, ignoreCase = true)
            }
        } ?: products
        items(filteredProducts.size) { index ->
            CardProduct(filteredProducts[index]) {
                action(it)
            }
        }
    }
}

