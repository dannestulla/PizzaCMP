package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.CardCheckout
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import presentation.model.PizzaProductUi

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardCheckout() {
    PizzaTheme {
        CardCheckout(
            PizzaProductUi(
                "Pizza",
                "asdasd",
                "Cheese, Onion, tomato",
                13.3,
                mapOf("onion" to 1, "tomato" to 2)
            )
        )
    }
}