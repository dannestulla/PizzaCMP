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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowUp
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.ui.theme.PizzaTheme


@Composable
fun RowAdd(isExpanded: (Boolean) -> Unit) {
    val expanded by remember {
        mutableStateOf(false)
    }
    isExpanded(expanded)
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
        if (expanded) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .border(1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .clickable {

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
}

@Preview
@Composable
fun RowAddPreview(modifier: Modifier = Modifier) {
    PizzaTheme {
        RowAdd() {

        }
    }
}