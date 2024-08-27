package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.CardProduct
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import presentation.model.PizzaProductUi

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardComponent() {
    PizzaTheme {
        CardProduct(
            PizzaProductUi(
                "",
                "Pepperoni",
                "Pepperoni, queijo, azeitona, pimentão e molho vermelho, " +
                        ", molho vermelho, molho vermelho, molho vermelho.",
                price = 23.23,
                mapOf("onion" to 1, "tomato" to 2)
            )
        ) {

        }
    }
}