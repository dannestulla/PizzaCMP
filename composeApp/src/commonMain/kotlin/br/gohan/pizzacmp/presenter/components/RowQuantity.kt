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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import org.jetbrains.compose.resources.painterResource
import pizzacmp.composeapp.generated.resources.Res
import pizzacmp.composeapp.generated.resources.minus

@Composable
fun RowQuantity(oldValue: Pair<String, Int>, newValue: (Pair<String, Int>) -> Unit) {
    var quantity by remember { mutableIntStateOf(0) }
    newValue(oldValue.first to quantity)

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = Dimens.paddingFromBorder)
            .height(64.dp)
            .fillMaxWidth()
            .wrapContentHeight()) {
        Text(text = oldValue.first, fontSize = Dimens.fontNormal)
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
                .clickable {
                    if (quantity != 0) {
                        quantity--
                    }
                }
        ) {
            Icon(
                painter = painterResource(Res.drawable.minus),
                contentDescription = "Minus",
                modifier = Modifier
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.width(Dimens.paddingInsideItems))
        Text(text = quantity.toString(), fontSize = Dimens.fontNormal)
        Spacer(modifier = Modifier.width(Dimens.paddingInsideItems))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
                .clickable {
                    quantity++
                }
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = rememberVectorPainter(Icons.Outlined.Add),
                contentDescription = "Add")
        }
    }
}
