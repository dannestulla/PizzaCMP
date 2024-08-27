package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens


@Composable
fun RowAdd(currentExpanded: Boolean, isExpanded: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = Dimens.paddingFromBorder)
            .height(64.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Toppings", fontSize = Dimens.fontLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        if (currentExpanded) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .border(1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .clickable {
                        isExpanded(false)
                    }
            ) {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    painter = rememberVectorPainter(Icons.Default.Done),
                    contentDescription = "Confirm toppings",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
                    .clickable {
                        isExpanded(true)
                    }
            ) {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    painter = rememberVectorPainter(Icons.Outlined.Add),
                    contentDescription = "Add"
                )
            }

        }
    }
    Box(
        modifier = Modifier.shadow(5.dp).height(2.dp).fillMaxWidth()
    )
}

