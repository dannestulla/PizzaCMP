package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.examples.screens.LoadingScreen
import mocks.products
import br.gohan.pizzacmp.presenter.components.ButtonPrimary
import br.gohan.pizzacmp.presenter.components.CardCheckout
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.presenter.components.RowTotalQuantity
import presentation.model.PizzaProductUi
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import domain.toCurrency
import org.koin.compose.koinInject
import presentation.CheckoutViewModel
import presentation.ProductViewModel

@Composable
fun CheckoutScreen(
    paddingValues: PaddingValues,
    viewModel: CheckoutViewModel = koinInject()
) {
    val products by viewModel.state.collectAsState()

    if (products.products == null) {
        LoadingScreen()
    } else {
        CheckoutScreenStateless(paddingValues, products.products!!)
    }
}

@Composable
fun CheckoutScreenStateless(
    paddingValues: PaddingValues,
    products: List<PizzaProductUi>
) {
    LazyColumn(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        items(products.size) {
            CardCheckout(products[it])
        }
        items(1) {
            Spacer(modifier = Modifier.height(40.dp))
            RowTotalQuantity(quantity = products.size.toString())
            val totalPrice = products.sumOf { it.price }
            RowInfo("Price", info = totalPrice.toCurrency())
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimary(label = "Confirm") {

            }
        }
    }
}

