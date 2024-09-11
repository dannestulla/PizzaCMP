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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import br.gohan.pizzacmp.database.DataStoreManager
import br.gohan.pizzacmp.presenter.components.CardProduct
import data.model.PizzaProduct
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import presentation.ui.theme.Dimens
import presentation.viewmodels.ProductsViewModel

@Composable
fun ProductsScreen(
    paddingValues: PaddingValues,
    currentSearch: String?,
    dataStore: DataStoreManager,
    viewModel: ProductsViewModel = koinViewModel(),
    navigateToProduct: () -> Unit
) {

    val coroutine = rememberCoroutineScope()

    val products by viewModel.state.collectAsState()
    if (products.products == null) {
        LoadingScreen()
        return
    } else {
        ProductsScreenStateless(
            paddingValues,
            products.products,
            currentSearch
        ) {
            coroutine.launch {
                dataStore.cacheProduct(it)
            }
            navigateToProduct()
        }
    }
}


@Composable
fun ProductsScreenStateless(
    paddingValues: PaddingValues,
    products: List<PizzaProduct>? = null,
    currentSearch: String?,
    selectedPizza: (PizzaProduct) -> Unit
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
                selectedPizza(it)
            }
        }
    }
}

