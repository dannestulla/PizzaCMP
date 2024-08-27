package br.gohan.pizzacmp.preview.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.screens.CheckoutScreenStateless
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import mocks.products

@Preview(showSystemUi = true)
@Composable
fun PreviewCheckoutScreen(modifier: Modifier = Modifier) {
    PizzaTheme {
        CheckoutScreenStateless(
            PaddingValues(),
            products
        )
    }
}