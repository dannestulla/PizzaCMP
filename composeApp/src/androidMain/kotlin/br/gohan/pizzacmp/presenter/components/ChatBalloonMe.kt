package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
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
fun ChatBalloonMe(message: String) {
    Card(
    ) {
        Column(
            Modifier.
            background(MaterialTheme.colorScheme.primaryContainer)
                .padding(Dimens.paddingSmall)
        ) {
            Text(text = message, fontSize = Dimens.fontSmall, color = MaterialTheme.colorScheme.onPrimary)
            Text(
                modifier = Modifier.align(Alignment.End).padding(top = 3.dp),
                text = "12:00", fontSize = Dimens.fontSmaller,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun PreviewChatBalloonMe() {
    PizzaTheme {
        ChatBalloonMe("This is a message test")
    }
}