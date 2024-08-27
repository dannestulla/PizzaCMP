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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.DataStoreManager
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.examples.screens.LoadingScreen
import br.gohan.pizzacmp.presenter.actions.ProductAction
import br.gohan.pizzacmp.presenter.components.ButtonPrimary
import br.gohan.pizzacmp.presenter.components.ImageLoader
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.presenter.components.SizeSelector
import domain.toCurrency
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import presentation.ProductViewModel
import presentation.model.PizzaProductUi

@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    pizzaProductUi: PizzaProductUi? = null
) {
    if (pizzaProductUi == null) {
        LoadingScreen()
    } else {
        ProductScreenStateless(paddingValues, pizzaProductUi) {
        }
    }
}

@Composable
fun ProductScreenStateless(
    paddingValues: PaddingValues,
    product: PizzaProductUi,
    action: (ProductAction) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surfaceContainer).verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(Dimens.paddingExtraLarge))
        ImageLoader(Modifier.width(300.dp))
        Spacer(modifier = Modifier.height(Dimens.paddingExtraLarge))
        Text(
            product.name,
            fontSize = Dimens.fontHuger,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(Dimens.paddingExtraLarge))
        Text(
            product.description,
            fontWeight = FontWeight.Medium,
            fontSize = Dimens.fontSmaller,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(Dimens.paddingMedium))
        SizeSelector()
        Spacer(modifier = Modifier.height(Dimens.paddingExtraHuge))
        RowInfo("Price", info = product.price.toCurrency())
        Spacer(modifier = Modifier.height(Dimens.paddingLarge))
        ButtonPrimary(label = "Add product") {
            action(ProductAction.Add(product))
        }
    }
}

