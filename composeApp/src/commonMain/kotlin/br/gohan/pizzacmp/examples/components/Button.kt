package br.gohan.pizzacmp.examples.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens

@Composable
fun Button(
    message: String,
    modifier: Modifier? = Modifier,
    action: () -> Unit
) {
    Button(colors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface,
    ),
        modifier = modifier
        !!.height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingSmall), onClick = {
            action()

        }) {
        Text(
            text = message, fontSize = Dimens.fontSmall, color = Color.White,
            style = TextStyle(
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            ),
        )
    }
}