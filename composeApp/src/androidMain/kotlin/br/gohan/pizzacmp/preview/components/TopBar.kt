package br.gohan.pizzacmp.preview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.components.TopBar
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import presentation.model.TopBarState

@Preview
@Composable
private fun Preview() {
    PizzaTheme {
        TopBar(
            state = TopBarState(
                title = "Preview",
                isSearchActive = false,
                noSearch = false
            )
        ) {

        }
    }
}