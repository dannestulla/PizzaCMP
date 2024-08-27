package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.RowAdd
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Preview
@Composable
fun RowAddPreview(modifier: Modifier = Modifier) {
    PizzaTheme {
        RowAdd(false) {

        }
    }
}