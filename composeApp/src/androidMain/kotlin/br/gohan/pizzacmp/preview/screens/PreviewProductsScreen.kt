package br.gohan.pizzacmp.preview.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import br.gohan.pizzacmp.presenter.screens.ProductsScreenStateless
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import mocks.products
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
private fun PreviewProductsScreen() {
    PizzaTheme {
        ProductsScreenStateless(
            products = products,
            currentSearch = null,
            paddingValues = PaddingValues()
        ) {

        }
    }
}