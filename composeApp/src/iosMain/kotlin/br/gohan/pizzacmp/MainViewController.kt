package br.gohan.pizzacmp

import androidx.compose.ui.window.ComposeUIViewController
import br.gohan.pizzacmp.database.NativeDataStoreManager


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    PizzaApp(NativeDataStoreManager())
}
