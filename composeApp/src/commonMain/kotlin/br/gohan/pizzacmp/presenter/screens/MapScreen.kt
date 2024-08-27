package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.examples.screens.LoadingScreen
import br.gohan.pizzacmp.presenter.components.ButtonSecondary
import br.gohan.pizzacmp.presenter.components.CardCheckout
import br.gohan.pizzacmp.presenter.components.MapFab
import br.gohan.pizzacmp.presenter.components.RowDeliver
import br.gohan.pizzacmp.presenter.components.RowInfo
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import org.koin.compose.koinInject
import presentation.MapViewModel
import presentation.model.Driver
import presentation.model.MapDirections

@Composable
fun MapScreen(
    viewModel: MapViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()

    if (state.driver == null || state.mapDirections == null) {
        LoadingScreen()
    } else {
        MapScreenStateless(state.driver!!, state.mapDirections!!)
    }
}

@Composable
fun MapScreenStateless(driver: Driver, mapDirections: MapDirections) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer).fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            val mapSize = (if (isExpanded) {
                Modifier.fillMaxSize()
            } else {
                Modifier.height(520.dp)
            }).background(androidx.compose.ui.graphics.Color.Green)
            Box(modifier = mapSize) // vai ser o mapa
            MapFab(modifier = Modifier.align(Alignment.BottomEnd)) {
                isExpanded = !isExpanded
            }
        }
        if (isExpanded.not()) {
            RowDeliver(driver.image, driver.name) {

            }
            RowInfo("Estimated time", info = mapDirections.estimatedTime)
            Spacer(modifier = (Modifier.height(16.dp)))
            ButtonSecondary(label = "Cancel delivery") {

            }
            Spacer(modifier = (Modifier.height(16.dp)))
        } else {
            MapFab() {
                isExpanded = !isExpanded
            }
        }
    }
}

