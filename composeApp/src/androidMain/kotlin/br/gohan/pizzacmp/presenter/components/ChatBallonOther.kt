package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Composable
fun ChatBalloonOther(message: String) {
    Card() {
        Column(Modifier.padding(Dimens.paddingSmall)) {
            Text(text = message, fontSize = Dimens.fontSmall)
            Text(
                modifier = Modifier.align(Alignment.End).padding(top = 3.dp),
                text = "12:00", fontSize = Dimens.fontSmaller,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }

}

@Preview
@Composable
private fun PreviewChatBalloonOne() {
    PizzaTheme {
        ChatBalloonOther("This is a message test")
    }
}