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
import androidx.compose.ui.unit.dp
import presentation.model.NewMessage
import presentation.ui.theme.Dimens

@Composable
fun ChatBalloonMe(
    modifier: Modifier = Modifier,
    message: NewMessage.Mine
) {
    Card(
        modifier
    ) {
        Column(
            modifier.
            background(MaterialTheme.colorScheme.primaryContainer)
                .padding(Dimens.paddingSmall)
        ) {
            Text(
                text = message.message,
                fontSize = Dimens.fontSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier.align(Alignment.End).padding(top = 3.dp),
                text = message.hour, fontSize = Dimens.fontSmaller,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }

}

