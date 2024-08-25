package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Composable
fun ButtonPrimary(modifier: Modifier = Modifier, label:String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingFromBorder),
        onClick = onClick
    ) {
        Text(text = label)
    }
}

@Preview
@Composable
private fun PreviewButtonPrimary() {
    PizzaTheme {
        ButtonPrimary(label = "Accept") {

        }
    }

}