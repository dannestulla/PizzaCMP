package br.gohan.pizzacmp.preview.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.screens.MapScreenStateless
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import mocks.driver
import mocks.mapDirections

@Preview(showBackground = true)
@Composable
private fun PreviewMapScreenStateless() {
    PizzaTheme {
        MapScreenStateless(
            driver,
            mapDirections
        )
    }
}