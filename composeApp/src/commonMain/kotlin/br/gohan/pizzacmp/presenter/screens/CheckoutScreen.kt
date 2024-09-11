package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.gohan.pizzacmp.presenter.actions.CheckoutAction
import br.gohan.pizzacmp.presenter.components.ButtonPrimary
import br.gohan.pizzacmp.presenter.components.CardCheckout
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.presenter.components.RowTotalQuantity
import data.model.PizzaSelected
import domain.toCurrency
import org.koin.compose.viewmodel.koinViewModel
import presentation.ui.theme.Dimens
import presentation.viewmodels.CheckoutViewModel

@Composable
fun CheckoutScreen(
    paddingValues: PaddingValues,
    viewModel: CheckoutViewModel = koinViewModel(),
    confirmOrder: () -> Unit
) {
    val products by viewModel.state.collectAsState()

    if (products.products == null) {
        LoadingScreen()
    } else {
        CheckoutScreenStateless(paddingValues, products.products!!) { action ->
            when (action) {
                is CheckoutAction.Remove -> viewModel.deleteCheckoutItem(action.selectionUi)
                is CheckoutAction.Toppings -> viewModel.updateToppings(action.selectionUi)
                is CheckoutAction.Order -> {
                    viewModel.sendOrder(action.selectionUi)
                    confirmOrder()
                }
            }
        }
    }
}

@Composable
fun CheckoutScreenStateless(
    paddingValues: PaddingValues,
    products: List<PizzaSelected>,
    action: (CheckoutAction) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement
            .spacedBy(Dimens.paddingInsideItemsSmall),
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        items(products.size) {
            CardCheckout(products[it], action)
        }
        item {
            HorizontalDivider()
            RowTotalQuantity(quantity = products.size.toString())
            val totalPrice = products.sumOf { it.priceSelected }
            RowInfo("Total", info = totalPrice.toCurrency())
            Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))
            ButtonPrimary(label = "Confirm order") {
                action(CheckoutAction.Order(products))
            }
            Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))
        }

    }
}
