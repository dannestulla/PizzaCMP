package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.RowDeliver
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Preview(showSystemUi = true)
@Composable
private fun PreviewRowDeliver() {
    PizzaTheme {
        RowDeliver(name = "Jose asdasdasdasdasdasdsCuervo") {

        }
    }
}