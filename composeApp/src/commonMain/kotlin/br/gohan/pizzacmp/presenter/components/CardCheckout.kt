package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import presentation.model.PizzaProductUi
import domain.toCurrency

@Composable
fun CardCheckout(selectionUi: PizzaProductUi) {
    var isToppingOptionsExpanded by remember { mutableStateOf(false) }
    val product by remember { mutableStateOf(selectionUi) }
    val toppingsSelected by remember { mutableStateOf(selectionUi.toppings.toMutableMap()) }

    Column(Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth()
                .height(140.dp)
                .padding(horizontal = Dimens.paddingFromBorder)
        ) {
            Card(
                modifier = Modifier.padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                ImageLoader(image = product.image)
            }
            Column(Modifier.padding(start = 16.dp, bottom = 6.dp, top = 6.dp)) {
                Text(product.name, fontSize = Dimens.fontHuge)
                Text(product.toppings.toString(), fontSize = Dimens.fontSmall)
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(
                        product.price.toCurrency(),
                        fontSize = Dimens.fontLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "ShoppingCard Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimens.paddingMedium))
        RowAdd(isToppingOptionsExpanded) { isExpanded ->
            isToppingOptionsExpanded = isExpanded
        }
        if (isToppingOptionsExpanded) {
            product.toppings.forEach { topping ->
                RowQuantity(topping.toPair()) { selected ->
                    toppingsSelected[selected.first] = selected.second
                }
            }
        }
    }
}


