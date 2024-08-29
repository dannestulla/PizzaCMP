package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.CardCheckout
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import mocks.products

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardCheckout() {
    PizzaTheme {
        CardCheckout(
            products.first()
        )
    }
}