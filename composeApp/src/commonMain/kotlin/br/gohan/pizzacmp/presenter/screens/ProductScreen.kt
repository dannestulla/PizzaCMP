package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.DataStoreManager
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.presenter.components.ButtonPrimary
import br.gohan.pizzacmp.presenter.components.ImageLoader
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.presenter.components.SizeSelector
import domain.toCurrency
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import presentation.ProductViewModel
import presentation.model.PizzaProductUi

@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    dataStore: DataStoreManager,
    viewModel: ProductViewModel = koinInject()
) {
    val coroutine = rememberCoroutineScope()

    var product by remember { mutableStateOf<PizzaProductUi?>(null) }

    coroutine.launch {
        product = dataStore.retrieveProduct()
    }

    if (product == null) {
        LoadingScreen()
    } else {
        ProductScreenStateless(paddingValues, product!!) {
            viewModel.addToCheckout(it)
        }
    }
}

@Composable
fun ProductScreenStateless(
    paddingValues: PaddingValues,
    product: PizzaProductUi,
    addProduct: (PizzaProductUi) -> Unit
) {
    val scrollState = rememberScrollState()

    var pizzaProduct by remember { mutableStateOf(product) }

    var buttonEnabled by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer).verticalScroll(scrollState)
            .padding(
                PaddingValues(
                    start = 0.dp,
                    end = 0.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
            )
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))
        ImageLoader(Modifier.width(300.dp))
        Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))
        Text(
            pizzaProduct.name,
            fontSize = Dimens.fontHuger,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))
        Text(
            pizzaProduct.description,
            fontWeight = FontWeight.Medium,
            fontSize = Dimens.fontSmaller,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(Dimens.paddingInsideItemsSmall))
        SizeSelector {
            pizzaProduct = pizzaProduct.copy(
                sizeSelected = it,
                priceSelected = pizzaProduct.prices[it.ordinal]
            )
        }
        Spacer(modifier = Modifier.height(Dimens.paddingInsideHuge))
        RowInfo("Price", info = pizzaProduct.priceSelected?.toCurrency() ?: "")
        Spacer(modifier = Modifier.height(Dimens.paddingInsideItems))
        ButtonPrimary(
            enabled = buttonEnabled,
            label = "Add Product"
        ) {
            addProduct(pizzaProduct)
            buttonEnabled = false
        }
        Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))

    }
}

