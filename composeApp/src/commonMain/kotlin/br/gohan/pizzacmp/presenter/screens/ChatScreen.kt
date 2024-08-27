package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.examples.screens.LoadingScreen
import br.gohan.pizzacmp.presenter.components.ButtonSecondary
import br.gohan.pizzacmp.presenter.components.ChatBalloonMe
import br.gohan.pizzacmp.presenter.components.ChatBalloonSomeone
import org.koin.compose.koinInject
import presentation.ChatViewModel
import presentation.model.Message

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinInject(),
    back: () -> Unit
) {
    val messages by viewModel.state.collectAsState()
    if (messages.message == null) {
        LoadingScreen()
    } else {
        ChatScreenStateless(messages.message!!, back = back)
    }
}

@Composable
fun ChatScreenStateless(messages: List<Message> = emptyList(), back: () -> Unit) {

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = Dimens.paddingFromBorder),
        verticalArrangement =
        Arrangement.spacedBy(Dimens.paddingInsideItemsSmall),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        messages.forEach { message ->
            when (message) {
                is Message.Someone -> ChatBalloonSomeone(
                    modifier = Modifier.align(alignment = Alignment.Start),
                    message = message
                )

                is Message.Mine -> ChatBalloonMe(
                    modifier = Modifier.align(alignment = Alignment.End),
                    message = message
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonSecondary(label = "Back to map") {
            back()
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
