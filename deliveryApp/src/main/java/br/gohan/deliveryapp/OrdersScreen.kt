package br.gohan.deliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import data.model.Order
import presentation.ui.theme.Dimens
import presentation.ui.theme.PizzaTheme

@Composable
fun OrdersScreen(
    innerPadding: PaddingValues,
    viewModel: DeliveryViewModel = viewModel()
) {
    val orders by viewModel.orders.collectAsStateWithLifecycle()
    OrdersScreenStateless(innerPadding, orders) { order ->
        viewModel.acceptOrder(order)
    }

}

@Composable
fun OrdersScreenStateless(
    innerPadding: PaddingValues = PaddingValues(),
    orders: List<Order>,
    modifier: Modifier = Modifier,
    acceptOrder: (Order) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Orders", fontSize = Dimens.fontLarge, fontWeight = FontWeight.Bold)
        LazyColumn(
            verticalArrangement = Arrangement
                .spacedBy(Dimens.paddingInsideItemsSmall),
        ) {
            items(orders.size) { index ->
                DeliveryCard(orders[index]) { order ->
                    acceptOrder(order)
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewOrdersScreen() {
    PizzaTheme {
        OrdersScreenStateless(orders = orders) {

        }
    }
}