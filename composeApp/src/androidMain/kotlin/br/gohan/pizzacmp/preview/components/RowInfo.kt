package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Preview
@Composable
fun RowPricePreview(modifier: Modifier = Modifier) {
    PizzaTheme {
        RowInfo("Price", "R$ 10.0")
    }
}