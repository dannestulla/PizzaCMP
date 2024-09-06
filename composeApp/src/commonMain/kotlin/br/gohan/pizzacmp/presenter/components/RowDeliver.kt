package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import presentation.ui.theme.Dimens

@Composable
fun RowDeliver(image: String? = null, name: String, startChat: () -> Unit) {
    Row(
        Modifier
            .height(75.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = Dimens.paddingFromBorder),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(60.dp)
                .padding(vertical = 9.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            ImageLoader(image = image)
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column {
            val maxLength = 14
            val displayText = if (name.length > maxLength) name.substring(0, maxLength) else name
            Text(text = displayText, fontSize = Dimens.fontHuge,maxLines = 1,
                fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
            Text(text = "Deliver")
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            modifier = Modifier
                .width(110.dp)
                .widthIn(110.dp),
            colors = ButtonDefaults.outlinedButtonColors()
                .copy(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            onClick = startChat
        ) {
            Text(
                text = "Chat",
                fontSize = Dimens.fontSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = rememberVectorPainter(Icons.Default.Email),
                contentDescription = "Confirm toppings",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

