package br.gohan.pizzacmp.preview.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.presenter.screens.ChatScreenStateless
import br.gohan.pizzacmp.ui.theme.PizzaTheme


@Preview(showBackground = true)
@Composable
private fun PreviewChatScreenStateless() {
    PizzaTheme {
        ChatScreenStateless(
            null,
            PaddingValues()
        ) {

        }
    }
}