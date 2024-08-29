package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.DeliverFab
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Preview
@Composable
private fun PreviewMapFab() {
    PizzaTheme {
        DeliverFab(expanded = false) {

        }
    }

}