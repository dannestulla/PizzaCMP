package br.gohan.deliveryapp.presenter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.gohan.deliveryapp.orders
import data.model.Order
import presentation.ui.theme.Dimens
import presentation.ui.theme.PizzaTheme

@Composable
fun DeliveryCard(order: Order, action: (Order) -> Unit) {

    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        onClick = { action(order) }
    ) {
        Column(
            Modifier.background(
                MaterialTheme.colorScheme.surfaceVariant,
            )
        ) {
            Spacer(modifier = Modifier.height(Dimens.paddingMedium))
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = Dimens.paddingFromBorder)
            ) {
                Column(Modifier.padding(start = 16.dp, bottom = 6.dp, top = 6.dp)) {
                    Text(
                        order.address,
                        fontSize = Dimens.fontLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(12.dp))

                    Row {
                        Text(
                            "Products: ",
                            fontSize = Dimens.fontNormal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(order.pizzas.toString(), fontSize = Dimens.fontNormal)
                    }
                    Spacer(Modifier.height(6.dp))

                    Row {
                        Text(
                            "Quantity of products: ",
                            fontSize = Dimens.fontNormal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(order.pizzas.size.toString(), fontSize = Dimens.fontNormal)
                    }
                    Spacer(Modifier.height(6.dp))
                    Row {
                        Text(
                            "Estimated Time: ",
                            fontSize = Dimens.fontNormal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(order.pizzas.size.toString(), fontSize = Dimens.fontNormal)
                    }
                    Spacer(Modifier.height(6.dp))
                    Row {
                        Text(
                            "Delivery Fee: ",
                            fontSize = Dimens.fontNormal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(order.deliveryFee ?: "Not calculated", fontSize = Dimens.fontNormal)
                    }
                }

            }
            Spacer(modifier = Modifier.height(Dimens.paddingMedium))

        }
    }
}

@Preview
@Composable
private fun PreviewDeliveryCard() {
    PizzaTheme {
        DeliveryCard(orders.first()) { }
    }
}

